package com.api.covoshcoffe.catalog.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.catalog.application.dto.request.CreateCategoryCommand;
import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;

public interface ManageCategoryUseCase {
    CategoryResponse createCategory(CreateCategoryCommand command);

    List<CategoryResponse> getAllCategories();

    CategoryResponse updateCategory(Integer id, String nombre, Boolean isActive);
}
