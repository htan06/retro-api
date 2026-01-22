package com.retro.api.dto.product.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.retro.api.entity.enums.ProductStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public class CreateProductDTO {
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    @JsonProperty("sku")
    private String sku;

    @NotBlank
    @JsonProperty("product_name")
    private String productName;

    @NotBlank
    private String thumbnail;

    @NotBlank
    private String summary;

    @NotBlank
    private String descriptions;

    @NotNull
    @JsonProperty("category_id")
    private UUID categoryId;

    @NotNull
    @JsonProperty("brand_id")
    private UUID brandId;

    @NotNull
    @DecimalMin("0.00")
    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    @Min(0)
    @Max(100)
    private int discount;
}
