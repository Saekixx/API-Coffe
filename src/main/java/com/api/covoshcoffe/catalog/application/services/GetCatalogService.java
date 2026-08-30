package com.api.covoshcoffe.catalog.application.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;
import com.api.covoshcoffe.catalog.application.ports.in.GetCatalogUseCase;
import com.api.covoshcoffe.catalog.domain.ports.out.ProductoRepositoryPort;
import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;
import com.api.covoshcoffe.catalog.domain.model.Producto;
import com.api.covoshcoffe.catalog.domain.ports.out.CategoriaRepositoryPort;

@Service
@Transactional(readOnly = true)
public class GetCatalogService implements GetCatalogUseCase {
    private final ProductoRepositoryPort productoRepositoryPort;
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public GetCatalogService(ProductoRepositoryPort productoRepositoryPort,
            CategoriaRepositoryPort categoriaRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public List<CategoryResponse> getActiveCategories() {
        return categoriaRepositoryPort.findAllActive()
                .stream()
                .map(cat -> new CategoryResponse(cat.id(), cat.nombre(), cat.isActive()))
                .toList();
    }

    @Override
    public List<ProductResponse> getActiveProducts() {
        return productoRepositoryPort.findAllActive()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Integer categoryId) {
        return productoRepositoryPort.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        return productoRepositoryPort.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    // Metodo para mapear un objeto Producto a ProductResponse
    private ProductResponse mapToResponse(Producto p) {
        CategoryResponse catResp = p.categoria() != null
                ? new CategoryResponse(p.categoria().id(), p.categoria().nombre(), p.categoria().isActive())
                : null;

        return new ProductResponse(
                p.id(),
                p.nombre(),
                p.descripcion(),
                p.precioBase(),
                p.imagenUrl(),
                p.isActive(),
                catResp);
    }
}
