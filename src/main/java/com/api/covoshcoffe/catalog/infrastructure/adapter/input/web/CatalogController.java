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
import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {
    private final GetCatalogUseCase getCatalogUseCase;

    public CatalogController(GetCatalogUseCase getCatalogUseCase) {
        this.getCatalogUseCase = getCatalogUseCase;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseGlobal<List<CategoryResponse>>> getActiveCategories() {
        return ResponseEntity.ok(ResponseGlobal.success(getCatalogUseCase.getActiveCategories()));
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseGlobal<List<ProductResponse>>> getActiveProducts() {
        return ResponseEntity.ok(ResponseGlobal.success(getCatalogUseCase.getActiveProducts()));
    }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<ResponseGlobal<List<ProductResponse>>> getProductsByCategory(
            @PathVariable Integer categoryId) {
        return ResponseEntity.ok(ResponseGlobal.success(getCatalogUseCase.getProductsByCategory(categoryId)));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseGlobal<ProductResponse>> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(ResponseGlobal.success(getCatalogUseCase.getProductById(id)));
    }
}
