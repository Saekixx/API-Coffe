package com.api.covoshcoffe.catalog.infrastructure.adapter.input.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.covoshcoffe.catalog.application.dto.request.CreateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;
import com.api.covoshcoffe.catalog.application.ports.in.ManageProductUseCase;

@RestController
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {
    private final ManageProductUseCase manageProductUseCase;

    public AdminProductController(ManageProductUseCase manageProductUseCase) {
        this.manageProductUseCase = manageProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductCommand command) {
        ProductResponse response = manageProductUseCase.createProduct(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Integer id,
            @RequestBody UpdateProductCommand command) {
        return ResponseEntity.ok(manageProductUseCase.updateProduct(id, command));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleProductStatus(
            @PathVariable Integer id,
            @RequestParam Boolean isActive) {
        manageProductUseCase.toggleProductStatus(id, isActive);
        return ResponseEntity.noContent().build();
    }
}
