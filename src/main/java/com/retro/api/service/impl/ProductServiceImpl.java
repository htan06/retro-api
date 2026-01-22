package com.retro.api.service.impl;

import com.retro.api.dto.product.request.*;
import com.retro.api.dto.product.response.ProductDetailsDTO;
import com.retro.api.dto.product.response.ProductOverviewDTO;
import com.retro.api.entity.*;
import com.retro.api.entity.enums.ProductStatus;
import com.retro.api.exception.CatalogException;
import com.retro.api.exception.CatalogExceptionEnum;
import com.retro.api.repository.BrandRepository;
import com.retro.api.repository.CategoryRepository;
import com.retro.api.repository.ProductRepository;
import com.retro.api.service.CloudinaryService;
import com.retro.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public ProductDetailsDTO createProduct(CreateProductDTO createProduct, MultipartFile thumbnail, List<MultipartFile> images) {
        if (productRepository.existsBySku(createProduct.getSku())) {
            throw new IllegalArgumentException("Sku already exists");
        }

        Category category = categoryRepository.findById(createProduct.getCategoryId())
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CATEGORY_NOT_FOUND));

        Brand brand = brandRepository.findById(createProduct.getBrandId())
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.BRAND_NOT_FOUND));

        String thumbnailUrl = cloudinaryService.uploadImage(thumbnail);
        List<String> image_urls = cloudinaryService.uploadImages(images);

        Product product = Product.builder()
                .sku(createProduct.getSku())
                .productName(createProduct.getProductName())
                .summary(createProduct.getSummary())
                .descriptions(createProduct.getDescriptions())
                .category(category)
                .brand(brand)
                .productStatus(ProductStatus.IN_STOCK)
                .salePrice(createProduct.getSalePrice())
                .discount(createProduct.getDiscount())
                .reviewCount(0)
                .totalRating(0)
                .build();

        Set<ProductImage> productImages = image_urls.stream()
                        .map(image_url -> ProductImage.builder()
                                .product(product)
                                .imageUrl(image_url)
                                .build())
                                .collect(Collectors.toSet());

        product.setThumbnail(thumbnailUrl);
        product.setProductImages(productImages);

        return ProductDetailsDTO.from(
                productRepository.save(product)
        );
    }

    @Override
    public ProductDetailsDTO getProductDetails(UUID id) {
        Product product = productRepository.findByIdAndProductStatus(id, ProductStatus.IN_STOCK)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.PRODUCT_NOT_FOUND));

        return ProductDetailsDTO.from(product);
    }

    @Override
    public List<ProductOverviewDTO> getListProductOverview() {
        return productRepository.findAllByProductStatus(ProductStatus.IN_STOCK)
                .stream()
                .map(ProductOverviewDTO::from)
                .toList();
    }

    @Override
    public ProductDetailsDTO updateProductInfo(UUID id, UpdateProductInfoDTO updateProductInfo) {
        Product product = findProductByStateNotDelete(id);

        product.setProductName(updateProductInfo.getProductName());
        product.setThumbnail(updateProductInfo.getThumbnail());
        product.setSummary(updateProductInfo.getSummary());
        product.setDescriptions(updateProductInfo.getDescriptions());

        return ProductDetailsDTO.from(
                productRepository.save(product)
        );
    }

    @Override
    public ProductDetailsDTO addRating(UUID id, AddRatingProductDTO addRatingProduct) {
        Product product = findProductByStateNotDelete(id);

        product.addRating(addRatingProduct.getRating());

        return ProductDetailsDTO.from(
                productRepository.save(product)
        );
    }

    @Override
    public ProductDetailsDTO updateSalePrice(UUID id, UpdateProductPriceDTO updateProductPrice) {
        Product product = findProductByStateNotDelete(id);

        product.setSalePrice(updateProductPrice.getNewPrice());

        return ProductDetailsDTO.from(
                productRepository.save(product)
        );
    }

    @Override
    public ProductDetailsDTO updateStatus(UUID id, UpdateProductStatusDTO updateProductStatus) {
        Product product = productRepository.findById(id)
                        .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.PRODUCT_NOT_FOUND));

        product.setProductStatus(updateProductStatus.getStatus());

        return ProductDetailsDTO.from(
                productRepository.save(product)
        );
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = findProductByStateNotDelete(id);
        product.setProductStatus(ProductStatus.DELETED); //soft delete
        productRepository.save(product);
    }

    private Product findProductInStock(UUID id) {
        return productRepository.findByIdAndProductStatus(id, ProductStatus.IN_STOCK)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.PRODUCT_NOT_FOUND));
    }

    private Product findProductByStateNotDelete(UUID id) {
        return productRepository.findByIdAndProductStatusNot(id, ProductStatus.DELETED)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.PRODUCT_NOT_FOUND));
    }
}
