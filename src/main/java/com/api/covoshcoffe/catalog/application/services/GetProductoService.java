package com.api.covoshcoffe.catalog.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.covoshcoffe.catalog.application.dto.response.ProductoDto;
import com.api.covoshcoffe.catalog.application.ports.in.GetProductoUseCase;
import com.api.covoshcoffe.catalog.domain.ports.out.ProductoRepositoryPort;

@Service
public class GetProductoService implements GetProductoUseCase {
    private final ProductoRepositoryPort productoRepositoryPort;

    public GetProductoService(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public List<ProductoDto> getAllProductos() {

        return productoRepositoryPort.findAll().stream()
                .map(producto -> new ProductoDto(
                        producto.id(),
                        producto.nombre(),
                        producto.descripcion(),
                        producto.precioBase(),
                        producto.categoria().id(),
                        producto.isNuevo() ? 1 : 0,
                        producto.isFrecuente() ? 1 : 0
                    ))
                .toList();
    }
}
