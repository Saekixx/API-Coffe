package com.api.covoshcoffe.catalog.domain.model;

public record Categoria(
        Integer id,
        String nombre,
        boolean isActive) {

    public Categoria(String nombre) {
        this(null, nombre, true);
    }
}
