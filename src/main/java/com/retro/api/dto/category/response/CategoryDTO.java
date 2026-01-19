package com.retro.api.dto.category.response;

import com.retro.api.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class CategoryDTO implements Serializable {
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
