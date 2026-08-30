package com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.entity.UsuarioEntity;

public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findById(String id);

    boolean existsByEmail(String email);
}
