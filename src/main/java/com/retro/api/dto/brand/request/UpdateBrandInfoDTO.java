package com.retro.api.dto.brand.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateBrandInfoDTO {
    @NotBlank
    private String name;
}
