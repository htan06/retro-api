package com.retro.api.service.impl;

import com.retro.api.dto.product.request.*;
import com.retro.api.dto.product.response.ProductDetailsDTO;
import com.retro.api.dto.product.response.ProductOverviewDTO;
import com.retro.api.entity.*;
import com.retro.api.entity.enums.ProductState;
import com.retro.api.exception.CatalogException;
import com.retro.api.exception.CatalogExceptionEnum;
import com.retro.api.repository.BrandRepository;
import com.retro.api.repository.CategoryRepository;
import com.retro.api.repository.ProductRepository;
import com.retro.api.service.ProductService;
import com.retro.api.utils.CreateSlug;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductDetailsDTO createProduct(CreateProductDTO createProduct) {
        Category category = categoryRepository.findById(createProduct.getCategoryId())
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CATEGORY_NOT_FOUND));

        Brand brand = brandRepository.findById(createProduct.getBrandId())
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.BRAND_NOT_FOUND));

        Product product = Product.builder()
                .sku(createProduct.getSku())
                .slug(CreateSlug.create(createProduct.getProductName()))
                .productName(createProduct.getProductName())
                .thumbnail(createProduct.getThumbnail())
                .summary(createProduct.getSummary())
                .descriptions(createProduct.getDescriptions())
                .category(category)
                .brand(brand)
                .productState(createProduct.getProductState())
                .salePrice(createProduct.getSalePrice())
                .discount(createProduct.getDiscount())
                .reviewCount(0)
                .totalRating(0)
                .build();

        Set<ProductImage> productImages = createProduct.getImageUrls()
                .stream()
                .map(i -> ProductImage.builder()
                        .product(product)
                        .imageUrl(i)
                        .build())
                .collect(Collectors.toSet());

        product.setProductImages(productImages);

        return ProductDetailsDTO.from(
                productRepository.save(product)
        );
    }

    @Override
    public ProductDetailsDTO getProductDetails(UUID id) {
        Product product = productRepository.findByIdAndProductState(id, ProductState.IN_STOCK)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.PRODUCT_NOT_FOUND));

        return ProductDetailsDTO.from(product);
    }

    @Override
    public List<ProductOverviewDTO> getListProductOverview() {
        return productRepository.findAllByProductState(ProductState.IN_STOCK)
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
    public ProductDetailsDTO updateState(UUID id, UpdateProductStateDTO updateProductState) {
        Product product = productRepository.findById(id)
                        .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.PRODUCT_NOT_FOUND));

        product.setProductState(updateProductState.getState());

        return ProductDetailsDTO.from(
                productRepository.save(product)
        );
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = findProductByStateNotDelete(id);
        product.setProductState(ProductState.DELETED); //soft delete
        productRepository.save(product);
    }

    private Product findProductInStock(UUID id) {
        return productRepository.findByIdAndProductState(id, ProductState.IN_STOCK)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.PRODUCT_NOT_FOUND));
    }

    private Product findProductByStateNotDelete(UUID id) {
        return productRepository.findByIdAndProductStateNot(id, ProductState.DELETED)
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.PRODUCT_NOT_FOUND));
    }
}
