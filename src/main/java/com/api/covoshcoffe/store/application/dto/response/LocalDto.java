package com.api.covoshcoffe.store.application.dto.response;

public record LocalDto(
        Integer id,
        String RazonSocial,
        String Direccion,
        Integer idDistrito,
        String horario,
        String latitud,
        String longitud) {

}
