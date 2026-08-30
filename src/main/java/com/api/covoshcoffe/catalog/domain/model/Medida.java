package com.api.covoshcoffe.catalog.domain.model;

public record Medida(
        Integer id,
        String nombre,
        Integer volumenMl, // Volumen en mililitros
        Double precioAdicional,
        boolean isActive) {
    public Medida(String nombre, Integer volumenMl, Double precioAdicional) {
        this(null, nombre, volumenMl, precioAdicional, true);
    }
}
