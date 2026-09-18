package com.api.covoshcoffe.store.application.dto.request;

public record CreateLocalCommand(
                String razonSocial,
                String direccion,
                Integer distritoId,
                Double latitud,
                Double longitud,
                String horario) {
}
