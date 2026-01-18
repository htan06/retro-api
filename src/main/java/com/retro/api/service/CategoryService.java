package com.retro.api.service;

import com.retro.api.dto.request.CreateCategoryDTO;
import com.retro.api.dto.request.UpdateCategoryInfoDTO;
import com.retro.api.dto.response.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getListCategory();

    CategoryDTO createCategory(CreateCategoryDTO createCategory);

    CategoryDTO updateCategory(UpdateCategoryInfoDTO updateCategoryInfo);
}
