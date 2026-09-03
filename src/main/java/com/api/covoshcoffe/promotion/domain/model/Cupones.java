package com.api.covoshcoffe.promotion.domain.model;

public record Cupones(
        Integer id,
        String codigo,
        Double descuento,
        Integer limiteUsos,
        Integer usosActuales,
        String fechaExpiracion,
        boolean activo) {

    public Cupones(String codigo, Double descuento, Integer limiteUsos, Integer usosActuales, String fechaExpiracion,
            boolean activo) {
        this(null, codigo, descuento, limiteUsos, usosActuales, fechaExpiracion, activo);
    }
}
