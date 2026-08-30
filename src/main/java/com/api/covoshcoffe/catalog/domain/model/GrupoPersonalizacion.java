package com.api.covoshcoffe.catalog.domain.model;

import java.util.List;

public record GrupoPersonalizacion(
        Integer id,
        String nombre,
        Boolean esObligatorio,
        Integer maxSeleccion,
        boolean isActive,
        List<OpcionPersonalizacion> personalizaciones) {
    public GrupoPersonalizacion(String nombre, Boolean esObligatorio, Integer maxSeleccion,
            List<OpcionPersonalizacion> personalizaciones) {
        this(null, nombre, esObligatorio, maxSeleccion, true, personalizaciones);
    }
}
