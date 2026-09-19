package com.api.covoshcoffe.catalog.application.dto.response;

public record ProductoDto(
        Integer id,
        String detalle,
        String descripcion,
        Double precio,
        Integer idCategoria,
        Integer nuevo,
        Integer frecuente) {

}
