package com.api.covoshcoffe.catalog.application.dto.response;

import java.util.List;

import com.api.covoshcoffe.catalog.domain.model.GrupoPersonalizacion;

public record GrupoPersonalizacionResponse(
                Long id,
                String nombre,
                Boolean esObligatorio,
                Integer maxSeleccion, 
                List<OpcionPersonalizacionResponse> opciones) {
        public static GrupoPersonalizacionResponse fromDomain(GrupoPersonalizacion grupo) {
                if (grupo == null)
                        return null;

                List<OpcionPersonalizacionResponse> opcionesResp = grupo.personalizaciones() != null
                                ? grupo.personalizaciones().stream()
                                                .map(OpcionPersonalizacionResponse::fromDomain)
                                                .toList()
                                : List.of();

                return new GrupoPersonalizacionResponse(
                                grupo.id() != null ? grupo.id().longValue() : null,
                                grupo.nombre(),
                                grupo.esObligatorio(),
                                grupo.maxSeleccion(), 
                                opcionesResp 
                );
        }
}