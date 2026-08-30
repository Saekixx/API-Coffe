package com.api.covoshcoffe.catalog.infrastructure.adapter.input.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;
import com.api.covoshcoffe.catalog.application.ports.in.GetCatalogUseCase;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {
    private final GetCatalogUseCase getCatalogUseCase;

    public CatalogController(GetCatalogUseCase getCatalogUseCase) {
        this.getCatalogUseCase = getCatalogUseCase;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getActiveCategories() {
        return ResponseEntity.ok(getCatalogUseCase.getActiveCategories());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getActiveProducts() {
        return ResponseEntity.ok(getCatalogUseCase.getActiveProducts());
    }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(getCatalogUseCase.getProductsByCategory(categoryId));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(getCatalogUseCase.getProductById(id));
    }
}
