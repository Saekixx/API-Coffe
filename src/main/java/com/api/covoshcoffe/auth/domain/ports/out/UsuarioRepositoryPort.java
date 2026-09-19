package com.api.covoshcoffe.auth.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.auth.domain.model.Usuario;

public interface UsuarioRepositoryPort {
    Usuario save(Usuario usuario);

    List<Usuario> findAll();

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(String id);

    boolean existsByEmail(String email);
}
