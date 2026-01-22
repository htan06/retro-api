package com.retro.api.dto.product.request;

import com.retro.api.entity.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateProductStatusDTO {
    @NotNull
    private ProductStatus status;
}
