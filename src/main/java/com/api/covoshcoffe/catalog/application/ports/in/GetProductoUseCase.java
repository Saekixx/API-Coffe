package com.api.covoshcoffe.catalog.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.catalog.application.dto.response.ProductoDto;

public interface GetProductoUseCase {
    List<ProductoDto> getAllProductos();
}
