package com.retro.api.dto.category.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCategoryInfoDTO {
    @NotBlank
    private String name;
}
