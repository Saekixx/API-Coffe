package com.api.covoshcoffe.catalog.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.covoshcoffe.catalog.application.dto.request.CreateCategoryCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateCategoryCommand;
import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;
import com.api.covoshcoffe.catalog.application.ports.in.ManageCategoryUseCase;
import com.api.covoshcoffe.catalog.domain.model.Categoria;
import com.api.covoshcoffe.catalog.domain.ports.out.CategoriaRepositoryPort;
import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;

@Service
@Transactional
public class ManageCategoryService implements ManageCategoryUseCase {
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public ManageCategoryService(CategoriaRepositoryPort categoriaRepositoryPort) {
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public java.util.List<CategoryResponse> getAllCategories() {
        return categoriaRepositoryPort.findAll()
                .stream()
                .map(cat -> new CategoryResponse(cat.id(), cat.nombre(), cat.isActive()))
                .toList();
    }

    @Override
    public CategoryResponse createCategory(CreateCategoryCommand command) {
        Categoria categoria = new Categoria(null, command.nombre(), true);
        Categoria guardada = categoriaRepositoryPort.save(categoria);
        return new CategoryResponse(guardada.id(), guardada.nombre(), guardada.isActive());
    }

    @Override
    public CategoryResponse updateCategory(Integer id, UpdateCategoryCommand command) {
        Categoria existente = categoriaRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Categoria actualizada = new Categoria(existente.id(), command.nombre(), existente.isActive());

        Categoria guardada = categoriaRepositoryPort.save(actualizada);
        return new CategoryResponse(guardada.id(), guardada.nombre(), guardada.isActive());
    }

    @Override
    public String toggleCategoryStatus(Integer id) {
        Categoria existente = categoriaRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Categoria actualizada = new Categoria(existente.id(), existente.nombre(), !existente.isActive());

        categoriaRepositoryPort.save(actualizada);
        return actualizada.isActive() ? "Categoría activada" : "Categoría desactivada";
    }
}
