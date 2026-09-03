package com.api.covoshcoffe.catalog.domain.model;

import java.util.Set;

public record Producto(
        Integer id,
        String nombre,
        String descripcion,
        Double precioBase,
        Categoria categoria,
        String imagenUrl,
        boolean isActive,
        Set<GrupoPersonalizacion> grupos) {

    public Producto {
        grupos = (grupos == null) ? Set.of() : Set.copyOf(grupos);
    }

    public Producto(String nombre, String descripcion, Double precioBase, Categoria categoria, String imagenUrl) {
        this(null, nombre, descripcion, precioBase, categoria, imagenUrl, true, Set.of());
    }
}
