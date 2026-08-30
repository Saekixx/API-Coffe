package com.api.covoshcoffe.catalog.application.dto.request;

public record CreateCustomizationGroupCommand(
        String nombre,
        Boolean esObligatorio,
        Integer maxOpciones) {
}
