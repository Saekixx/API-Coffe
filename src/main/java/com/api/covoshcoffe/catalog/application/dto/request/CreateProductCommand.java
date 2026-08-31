package com.api.covoshcoffe.catalog.application.dto.request;

public record CreateProductCommand(
                String nombre,
                String descripcion,
                Double precioBase,
                Integer categoriaId) {
}
