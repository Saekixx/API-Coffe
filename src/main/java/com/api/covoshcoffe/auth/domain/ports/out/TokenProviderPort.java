package com.api.covoshcoffe.auth.domain.ports.out;

import com.api.covoshcoffe.auth.domain.model.Usuario;

public interface TokenProviderPort {
    // Generar un token JWT para un usuario
    String generarToken(Usuario usuario);

    // Obtener el email del usuario a partir del token JWT
    String getEmailFromToken(String token);

    // Validar si el token JWT es válido
    boolean validarToken(String token);
}
