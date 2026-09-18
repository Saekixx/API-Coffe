package com.api.covoshcoffe.store.domain.model;

public record Local(
                Integer id,
                String razonSocial,
                String direccion,
                Double latitud,
                Double longitud,
                String horario,
                boolean isActive,
                Distrito distrito) {
        public static Local crearNuevoLocal(String razonSocial, String direccion, Double latitud, Double longitud,
                        String horario, Distrito distrito) {
                return new Local(null, razonSocial, direccion, latitud, longitud, horario, true, distrito);
        }

        public static Local toggleStatus(Local local) {
                return new Local(local.id(), local.razonSocial(), local.direccion(), local.latitud(), local.longitud(),
                                local.horario(), !local.isActive(), local.distrito());
        }
}
