package com.retro.api.dto.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.retro.api.dto.brand.response.BrandDTO;
import com.retro.api.dto.category.response.CategoryDTO;
import com.retro.api.entity.Product;
import com.retro.api.entity.ProductImage;
import com.retro.api.entity.enums.ProductState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ProductDetailsDTO implements Serializable {
    private UUID id;

    private String sku;

    private String slug;

    @JsonProperty("product_name")
    private String productName;

    private String thumbnail;

    private String summary;

    private String descriptions;

    private CategoryDTO category;

    private BrandDTO brand;

    @JsonProperty("product_state")
    private ProductState productState;

    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    private Integer discount;

    @JsonProperty("average_rating")
    private double averageRating;

    @JsonProperty("image_urls")
    private List<String> imageUrls;

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
