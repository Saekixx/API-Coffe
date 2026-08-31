package com.api.covoshcoffe.catalog.application.dto.request;

public record UpdateProductCommand(
                String nombre,
                String descripcion,
                Double precioBase,
                Integer categoriaId,
                Boolean isActive) {
}
