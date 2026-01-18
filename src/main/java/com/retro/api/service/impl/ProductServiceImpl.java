package com.retro.api.service.impl;

import com.retro.api.dto.request.*;
import com.retro.api.dto.response.ProductDetailsDTO;
import com.retro.api.dto.response.ProductOverviewDTO;
import com.retro.api.entity.*;
import com.retro.api.exception.CatalogException;
import com.retro.api.exception.CatalogExceptionEnum;
import com.retro.api.repository.BrandRepository;
import com.retro.api.repository.CategoryRepository;
import com.retro.api.repository.ProductRepository;
import com.retro.api.service.ProductService;
import com.retro.api.utils.CreateSlug;
import jakarta.persistence.*;
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
                .orElseThrow(() -> new CatalogException(CatalogExceptionEnum.CATEGORY_NOT_FOUND));

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
                .isDelete(false)
                .build();

        Set<ProductImage> productImages = createProduct.getImageUrl()
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
        return null;
    }

    @Override
    public List<ProductOverviewDTO> getListProductOverview() {
        return List.of();
    }

    @Override
    public ProductDetailsDTO updateProductInfo(UpdateProductInfoDTO updateProductInfo) {
        return null;
    }

    @Override
    public ProductDetailsDTO addRating(AddRatingProductDTO addRatingProduct) {
        return null;
    }

    @Override
    public ProductDetailsDTO updatePrice(UpdateProductPriceDTO updateProductPrice) {
        return null;
    }

    @Override
    public ProductDetailsDTO updateState(UpdateProductStateDTO updateProductState) {
        return null;
    }

    @Override
    public void deleteProduct(UUID id) {

    }
}
