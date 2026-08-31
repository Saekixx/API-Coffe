package com.api.covoshcoffe.catalog.infrastructure.adapter.input.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.covoshcoffe.catalog.application.dto.request.CreateCategoryCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateCategoryCommand;
import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;
import com.api.covoshcoffe.catalog.application.ports.in.ManageCategoryUseCase;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/admin/categories")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCategorieController {
    private final ManageCategoryUseCase manageCategoryUseCase;

    public AdminCategorieController(ManageCategoryUseCase manageCategoryUseCase) {
        this.manageCategoryUseCase = manageCategoryUseCase;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> allCategories() {
        List<CategoryResponse> categories = manageCategoryUseCase.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CreateCategoryCommand command) {
        CategoryResponse response = manageCategoryUseCase.createCategory(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Integer id,
            @RequestBody UpdateCategoryCommand command) {
        CategoryResponse response = manageCategoryUseCase.updateCategory(command);
        return ResponseEntity.ok(response);
    }
}
