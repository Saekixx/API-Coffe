package com.api.covoshcoffe.catalog.application.dto.response;

public record CustomizationOptionResponse(
        Integer id,
        String nombre,
        Double precioAdicional,
        Boolean isActive) {
}
