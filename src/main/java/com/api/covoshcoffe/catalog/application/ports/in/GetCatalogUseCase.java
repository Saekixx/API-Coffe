package com.api.covoshcoffe.catalog.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;

public interface GetCatalogUseCase {
    List<CategoryResponse> getActiveCategories();

    List<ProductResponse> getActiveProducts();

    List<ProductResponse> getProductsByCategory(Integer categoryId);

    ProductResponse getProductById(Integer id);
}
