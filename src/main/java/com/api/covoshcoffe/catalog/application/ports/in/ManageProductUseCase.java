package com.api.covoshcoffe.catalog.application.ports.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.covoshcoffe.catalog.application.dto.request.CreateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;

public interface ManageProductUseCase {
    ProductResponse createProduct(CreateProductCommand command, MultipartFile imageFile);

    ProductResponse updateProduct(Integer id, UpdateProductCommand command, MultipartFile imageFile);

    List<ProductResponse> getAllProducts();

    String toggleProductStatus(Integer id);
}
