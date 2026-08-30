package com.api.covoshcoffe.catalog.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.covoshcoffe.catalog.application.dto.request.CreateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.request.UpdateProductCommand;
import com.api.covoshcoffe.catalog.application.dto.response.CategoryResponse;
import com.api.covoshcoffe.catalog.application.dto.response.ProductResponse;
import com.api.covoshcoffe.catalog.application.ports.in.ManageProductUseCase;
import com.api.covoshcoffe.catalog.domain.model.Categoria;
import com.api.covoshcoffe.catalog.domain.model.Producto;
import com.api.covoshcoffe.catalog.domain.ports.out.CategoriaRepositoryPort;
import com.api.covoshcoffe.catalog.domain.ports.out.ProductoRepositoryPort;
import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;

@Service
@Transactional
public class ManageProductService implements ManageProductUseCase {
    private final ProductoRepositoryPort productoRepositoryPort;
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public ManageProductService(ProductoRepositoryPort productoRepositoryPort,
            CategoriaRepositoryPort categoriaRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public ProductResponse createProduct(CreateProductCommand command) {
        Categoria categoria = categoriaRepositoryPort.findById(command.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Producto producto = new Producto(
                null,
                command.nombre(),
                command.descripcion(),
                command.precioBase(),
                categoria,
                command.imagenUrl(),
                true);

        Producto guardado = productoRepositoryPort.save(producto);
        return mapToResponse(guardado);
    }

    @Override
    public ProductResponse updateProduct(Integer id, UpdateProductCommand command) {
        Producto existente = productoRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Categoria categoria = categoriaRepositoryPort.findById(command.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Producto productoActualizado = new Producto(
                existente.id(),
                command.nombre(),
                command.descripcion(),
                command.precioBase(),
                categoria,
                command.imagenUrl(),
                command.isActive());

        Producto guardado = productoRepositoryPort.save(productoActualizado);
        return mapToResponse(guardado);
    }

    @Override
    public void toggleProductStatus(Integer id, Boolean isActive) {
        Producto producto = productoRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Producto productoModificado = new Producto(
                producto.id(),
                producto.nombre(),
                producto.descripcion(),
                producto.precioBase(),
                producto.categoria(),
                producto.imagenUrl(),
                isActive);

        productoRepositoryPort.save(productoModificado);
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
