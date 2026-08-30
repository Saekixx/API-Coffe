package com.api.covoshcoffe.auth.domain.ports;

import java.util.Optional;

import com.api.covoshcoffe.auth.domain.model.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(String id);

    boolean existsByEmail(String email);
}
