package com.retro.api.dto.response;

import com.retro.api.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ProductOverviewDTO {
    private UUID id;
    private String slug;
    private String productName;
    private String thumbnail;
    private double averageRating;

    public static ProductOverviewDTO from(Product product) {
        return new ProductOverviewDTO(
                product.getId(),
                product.getSlug(),
                product.getProductName(),
                product.getThumbnail(),
                product.calAverageRating()
        );
    }
}
