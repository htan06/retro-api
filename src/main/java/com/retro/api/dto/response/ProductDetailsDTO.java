package com.retro.api.dto.response;

import com.retro.api.entity.Product;
import com.retro.api.entity.ProductImage;
import com.retro.api.entity.ProductState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ProductDetailsDTO {
    private UUID id;
    private String sku;
    private String slug;
    private String productName;
    private String thumbnail;
    private String summary;
    private String descriptions;
    private CategoryDTO category;
    private BrandDTO brand;
    private ProductState productState;
    private BigDecimal salePrice;
    private Integer discount;
    private double averageRating;
    private List<String> imageUrl;

    public static ProductDetailsDTO from(Product product) {
        return new ProductDetailsDTO(
                product.getId(),
                product.getSku(),
                product.getSlug(),
                product.getProductName(),
                product.getThumbnail(),
                product.getSummary(),
                product.getDescriptions(),
                CategoryDTO.from(product.getCategory()),
                BrandDTO.from(product.getBrand()),
                product.getProductState(),
                product.getSalePrice(),
                product.getDiscount(),
                product.calAverageRating(),
                product.getProductImages().stream().map(ProductImage::getImageUrl).toList()
        );
    }
}
