package com.api.covoshcoffe.catalog.application.ports.in;

import com.api.covoshcoffe.catalog.application.dto.request.CreateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;

public interface ManageProductUseCase {
    ProductResponse createProduct(CreateProductCommand command);

    ProductResponse updateProduct(Integer id, UpdateProductCommand command);

    void toggleProductStatus(Integer id, Boolean isActive);
}
