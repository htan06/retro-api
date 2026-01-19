package com.retro.api.dto.brand.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateBrandDTO {
    @NotBlank
    private String name;
}
