package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.store.domain.model.Distrito;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity.DistritoEntity;

public class DistritoPersistenceMapper {
    public static Distrito toDomain(DistritoEntity entity) {
        if (entity == null)
            return null;

        return new Distrito(
                entity.getId(),
                entity.getDetalle());
    }

    public static DistritoEntity toEntity(Distrito domain) {
        if (domain == null)
            return null;

        DistritoEntity entity = new DistritoEntity();
        entity.setId(domain.id());
        entity.setDetalle(domain.detalle());

        return entity;
    }
}
