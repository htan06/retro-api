package com.retro.api.dto.request;

import com.retro.api.entity.ProductState;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateProductStateDTO {
    private UUID productId;
    private ProductState state;
}
