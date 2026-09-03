package com.api.covoshcoffe.catalog.application.dto.request;

import java.util.List;

public record UpdateProductCommand(
        String nombre,
        String descripcion,
        Double precioBase,
        Integer categoriaId,
        Boolean isActive,
        List<Integer> grupoIds) {
}
