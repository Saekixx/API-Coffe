package com.api.covoshcoffe.order.application.dtos.response;

public record OpcionPersonalizacionDto(
        Integer id,
        Integer idGrupo,
        String nombre,
        Double precioAdicional) {

}
