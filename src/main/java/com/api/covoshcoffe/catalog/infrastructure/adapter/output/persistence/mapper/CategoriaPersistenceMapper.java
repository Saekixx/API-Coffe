package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.catalog.domain.model.Categoria;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.CategoriaEntity;

public class CategoriaPersistenceMapper {
    public static Categoria toDomain(CategoriaEntity entity) {
        if (entity == null)
            return null;
        return new Categoria(
                entity.getId(),
                entity.getNombre(),
                entity.isActive());
    }

    public static CategoriaEntity toEntity(Categoria domain) {
        if (domain == null)
            return null;
        return new CategoriaEntity(
                domain.id(),
                domain.nombre(),
                domain.isActive());
    }
}
