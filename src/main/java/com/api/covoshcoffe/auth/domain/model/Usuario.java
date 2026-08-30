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
        Rol rol,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public Usuario(String fullname, String email, String password, AuthProveedor proveedorAuth, String proveedorId,
            Integer puntos, Rol rol) {
        this(
                null,
                fullname,
                email,
                password,
                proveedorAuth,
                proveedorId,
                puntos,
                rol,
                null,
                null);
    }
}
