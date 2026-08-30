package com.api.covoshcoffe.auth.domain.ports;

public interface PasswordEncoderPort {
    // Encritpar una contraseña
    String encriptar(String password);

    // Verificar si una contraseña coincide con su versión encriptada
    boolean matches(String rawPassword, String encodedPassword);
}
