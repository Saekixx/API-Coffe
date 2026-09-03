package com.api.covoshcoffe.catalog.infrastructure.adapter.input.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.covoshcoffe.catalog.application.dto.request.CreateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;
import com.api.covoshcoffe.catalog.application.ports.in.ManageProductUseCase;
import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/admin/products")
@PreAuthorize("hasRole('ADMIN')")
public class AdminProductController {
    private final ManageProductUseCase manageProductUseCase;

    public AdminProductController(ManageProductUseCase manageProductUseCase) {
        this.manageProductUseCase = manageProductUseCase;
    }

    @GetMapping
    public ResponseEntity<ResponseGlobal<List<ProductResponse>>> allProducts() {
        List<ProductResponse> response = manageProductUseCase.getAllProducts();
        return ResponseEntity.ok(ResponseGlobal.success(response));
    }

    @PostMapping
    public ResponseEntity<ResponseGlobal<ProductResponse>> createProduct(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart("data") CreateProductCommand command) {
        ProductResponse response = manageProductUseCase.createProduct(command, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseGlobal.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGlobal<ProductResponse>> updateProduct(
            @PathVariable Integer id,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart("data") UpdateProductCommand command) {
        ProductResponse response = manageProductUseCase.updateProduct(id, command, file);
        return ResponseEntity.ok(ResponseGlobal.success(response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseGlobal<String>> toggleProductStatus(
            @PathVariable Integer id) {
        String response = manageProductUseCase.toggleProductStatus(id);
        return ResponseEntity.ok(ResponseGlobal.success(response));
    }
}
