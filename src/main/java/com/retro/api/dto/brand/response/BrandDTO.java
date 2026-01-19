package com.retro.api.dto.brand.response;

import com.retro.api.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class BrandDTO implements Serializable {
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
