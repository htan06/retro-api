package com.retro.api.dto.request;

import com.retro.api.entity.ProductState;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public class CreateProductDTO {
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_-]+$")
    private String sku;

    @NotBlank
    private String productName;

    @NotBlank
    private String thumbnail;

    @NotBlank
    private String summary;

    @NotBlank
    private String descriptions;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID brandId;

    @NotNull
    private ProductState productState;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal salePrice;

    @Min(0)
    @Max(100)
    private int discount;

    @NotNull
    private List<@NotBlank String> imageUrl;
}
