package com.retro.api.dto.product.request;

import com.retro.api.entity.enums.ProductState;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateProductStateDTO {
    @NotNull
    private ProductState state;
}
