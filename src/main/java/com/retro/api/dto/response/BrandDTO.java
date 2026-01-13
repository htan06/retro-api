package com.retro.api.dto.response;

import com.retro.api.entity.Brand;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class BrandDTO {
    private UUID id;
    private String name;
    private String slug;

    public static BrandDTO from(Brand brand) {
        return new BrandDTO(
                brand.getId(),
                brand.getBrandName(),
                brand.getSlug()
        );
    }
}
