package com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.mapper;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.auth.domain.model.Usuario;
import com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.entity.UsuarioEntity;

@Component
public class UsuarioPersistenceMapper {
    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null)
            return null;
        return new Usuario(
                entity.getId(),
                entity.getFullname(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getProveedorAuth(),
                entity.getProveedorId(),
                entity.getPuntos(),
                entity.isActive(),
                entity.getRol(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public UsuarioEntity toEntity(Usuario domain) {
        if (domain == null)
            return null;
        return new UsuarioEntity(
                domain.id(),
                domain.fullname(),
                domain.email(),
                domain.password(),
                domain.proveedorAuth(),
                domain.proveedorId(),
                domain.puntos(),
                domain.isActive(),
                domain.rol(),
                domain.createdAt(),
                domain.updatedAt());
    }
}
