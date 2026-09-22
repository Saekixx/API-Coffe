package com.api.covoshcoffe.catalog.domain.dtos;

public record OpcionPersonalizacionDto(
        Integer id,
        Integer idGrupo,
        String nombre,
        Double precioAdicional
) {
}
