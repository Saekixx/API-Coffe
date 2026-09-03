package com.api.covoshcoffe.promotion.application.dto.request;

public record UpdateCuponesRequest(
        String codigo,
        Double descuento,
        Integer limiteUsos,
        Integer usosActuales,
        String fechaExpiracion) {

}
