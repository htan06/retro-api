package com.retro.api.dto.response;

import com.retro.api.entity.Category;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class CategoryDTO {
    private UUID id;
    private String name;
    private String slug;

    public static CategoryDTO from(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getCategoryName(),
                category.getSlug()
        );
    }
}
