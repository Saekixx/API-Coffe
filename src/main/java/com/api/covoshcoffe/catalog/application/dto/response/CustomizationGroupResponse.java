package com.api.covoshcoffe.catalog.application.dto.response;

import java.util.List;

public record CustomizationGroupResponse(
        Integer id,
        String nombre,
        Boolean esObligatorio,
        Integer maxOpciones,
        Boolean isActive,
        List<CustomizationOptionResponse> opciones) {
}
