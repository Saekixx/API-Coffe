package com.api.covoshcoffe.store.domain.model;

import java.time.LocalTime;

public record Local(
                Integer id,
                String nombre,
                String direccion,
                String ciudad,
                Double latitud,
                Double longitud,
                LocalTime horarioApertura,
                LocalTime horarioCierre,
                boolean isActive) {
        public static Local crearNuevoLocal(String nombre, String direccion, String ciudad, Double latitud,
                        Double longitud, LocalTime horarioApertura, LocalTime horarioCierre) {
                return new Local(null, nombre, direccion, ciudad, latitud, longitud, horarioApertura, horarioCierre,
                                true);
        }
}
