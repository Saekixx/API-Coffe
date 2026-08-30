package com.api.covoshcoffe.catalog.domain.model;

public record OpcionPersonalizacion(
        Integer id,
        String nombre,
        Double precioAdicional,
        GrupoPersonalizacion grupoPersonalizacion) {
    public OpcionPersonalizacion(String nombre, Double precioAdicional, GrupoPersonalizacion grupoPersonalizacion) {
        this(null, nombre, precioAdicional, grupoPersonalizacion);
    }
}
