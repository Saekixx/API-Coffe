package com.api.covoshcoffe.store.application.dto.request;

import java.time.LocalTime;

public record CreateLocalCommand(
        String nombre,
        String direccion,
        String ciudad,
        Double latitud,
        Double longitud,
        LocalTime horarioApertura,
        LocalTime horarioCierre) {
}
