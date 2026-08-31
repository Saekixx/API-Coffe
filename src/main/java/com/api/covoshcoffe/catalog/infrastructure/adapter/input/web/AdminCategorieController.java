package com.api.covoshcoffe.catalog.infrastructure.adapter.input.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.covoshcoffe.catalog.application.dto.request.CreateCategoryCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateCategoryCommand;
import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;
import com.api.covoshcoffe.catalog.application.ports.in.ManageCategoryUseCase;
import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<ResponseGlobal<List<CategoryResponse>>> allCategories() {
        List<CategoryResponse> categories = manageCategoryUseCase.getAllCategories();
        return ResponseEntity.ok(ResponseGlobal.success(categories));
    }

    @PostMapping
    public ResponseEntity<ResponseGlobal<CategoryResponse>> createCategory(@RequestBody CreateCategoryCommand command) {
        CategoryResponse response = manageCategoryUseCase.createCategory(command);
        return ResponseEntity.ok(ResponseGlobal.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGlobal<CategoryResponse>> updateCategory(
            @PathVariable Integer id,
            @RequestBody UpdateCategoryCommand command) {
        CategoryResponse response = manageCategoryUseCase.updateCategory(id, command);
        return ResponseEntity.ok(ResponseGlobal.success(response));
    }
}
