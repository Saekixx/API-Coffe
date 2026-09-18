package com.api.covoshcoffe.store.application.dto.request;

import java.time.LocalTime;

public record UpdateLocalCommand(
                String razonSocial,
                String direccion,
                Integer distritoId,
                Double latitud,
                Double longitud,
                String horario) {
}