package com.api.covoshcoffe.catalog.application.dto.request;

import java.util.List;

public record CreateProductCommand(
        String nombre,
        String descripcion,
        Double precioBase,
        Integer categoriaId,
        List<Integer> grupoIds) {
}
