package com.api.covoshcoffe.catalog.domain.dtos;

public record GrupoPersonalizacionDto(
        Integer id,
        String nombre,
        Integer esObligatorio,
        Integer maxSeleccion
) {
}
