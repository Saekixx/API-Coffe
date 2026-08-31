package com.api.covoshcoffe.store.domain.model;

import java.time.LocalTime;

public record Local(
        String nombre,
        String direccion,
        String ciudad,
        Double latitud,
        Double longitud,
        LocalTime horarioApertura,
        LocalTime horarioCierre,
        boolean isActive) {

}
