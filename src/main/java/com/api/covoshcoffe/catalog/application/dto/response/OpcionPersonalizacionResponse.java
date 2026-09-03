package com.api.covoshcoffe.catalog.application.dto.response;

import com.api.covoshcoffe.catalog.domain.model.OpcionPersonalizacion;

public record OpcionPersonalizacionResponse(
                Integer id,
                String nombre,
                Double precioAdicional) {
        public static OpcionPersonalizacionResponse fromDomain(OpcionPersonalizacion opcion) {
                if (opcion == null)
                        return null;
                return new OpcionPersonalizacionResponse(
                                opcion.id(),
                                opcion.nombre(),
                                opcion.precioAdicional());
        }
}
