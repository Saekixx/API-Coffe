package com.api.covoshcoffe.auth.domain.model;

import java.time.LocalDateTime;

public record Usuario(
        Integer id,
        String fullname,
        String email,
        String password,
        AuthProveedor proveedorAuth,
        String proveedorId,
        Integer puntos,
        boolean isActive,
        Rol rol,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static Usuario crearNuevoLocal(String fullname, String email, String password) {
        return new Usuario(
                null,
                fullname,
                email,
                password,
                AuthProveedor.LOCAL,
                null,
                0,
                true,
                Rol.CLIENTE,
                null,
                null);
    }
}
