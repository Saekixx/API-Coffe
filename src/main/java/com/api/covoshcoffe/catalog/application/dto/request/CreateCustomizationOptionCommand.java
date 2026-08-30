package com.api.covoshcoffe.catalog.application.dto.request;

public record CreateCustomizationOptionCommand(
        Integer grupoId,
        String nombre,
        Double precioAdicional) {
}