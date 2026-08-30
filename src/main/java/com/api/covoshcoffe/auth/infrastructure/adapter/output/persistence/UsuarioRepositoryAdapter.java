package com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.auth.domain.model.Usuario;
import com.api.covoshcoffe.auth.domain.ports.out.UsuarioRepositoryPort;
import com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.mapper.UsuarioPersistenceMapper;
import com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.repository.SpringDataUsuarioRepository;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    private final SpringDataUsuarioRepository usuarioRepository;
    private final UsuarioPersistenceMapper usuarioMapper;

    public UsuarioRepositoryAdapter(SpringDataUsuarioRepository usuarioRepository,
            UsuarioPersistenceMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario save(Usuario usuario) {
        var entity = usuarioMapper.toEntity(usuario);
        var savedEntity = usuarioRepository.save(entity);
        return usuarioMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        var entity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el email: " + email));
        return Optional.of(usuarioMapper.toDomain(entity));
    }

    @Override
    public Optional<Usuario> findById(String id) {
        var entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el id: " + id));
        return Optional.of(usuarioMapper.toDomain(entity));
    }

}
