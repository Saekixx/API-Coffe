package com.api.covoshcoffe.catalog.application.dto.response;

public record ProductResponse(
        Integer id,
        String nombre,
        String descripcion,
        Double precioBase,
        String imagenUrl,
        boolean isActive,
        CategoryResponse categoria) {
}
