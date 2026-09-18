package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.store.domain.model.Local;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity.LocalEntity;

public class LocalPersistenceMapper {
    public static Local toDomain(LocalEntity entity) {
        if (entity == null)
            return null;

        return new Local(
                entity.getId(),
                entity.getRazonSocial(),
                entity.getDireccion(),
                entity.getLatitud(),
                entity.getLongitud(),
                entity.getHorario(),
                entity.isActive(),
                DistritoPersistenceMapper.toDomain(entity.getDistrito()));
    }

    public static LocalEntity toEntity(Local domain) {
        if (domain == null)
            return null;

        LocalEntity entity = new LocalEntity();
        entity.setId(domain.id());
        entity.setRazonSocial(domain.razonSocial());
        entity.setDireccion(domain.direccion());
        entity.setLatitud(domain.latitud());
        entity.setLongitud(domain.longitud());
        entity.setHorario(domain.horario());
        entity.setActive(domain.isActive());
        entity.setDistrito(DistritoPersistenceMapper.toEntity(domain.distrito()));

        return entity;
    }
}
