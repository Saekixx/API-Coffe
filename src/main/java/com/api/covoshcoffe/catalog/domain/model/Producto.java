package com.api.covoshcoffe.catalog.domain.model;

public record Producto(
        Integer id,
        String nombre,
        String descripcion,
        Double precioBase,
        Categoria categoria,
        String imagenUrl,
        boolean isActive) {

    public Producto(String nombre, String descripcion, Double precioBase, Categoria categoria, String imagenUrl) {
        this(null, nombre, descripcion, precioBase, categoria, imagenUrl, true);
    }
}
