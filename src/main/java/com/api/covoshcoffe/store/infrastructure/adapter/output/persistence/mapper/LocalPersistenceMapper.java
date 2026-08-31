package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.store.domain.model.Local;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity.LocalEntity;

public class LocalPersistenceMapper {
    public static Local toDomain(LocalEntity entity) {
        if (entity == null)
            return null;
        return new Local(
                entity.getId(),
                entity.getNombre(),
                entity.getDireccion(),
                entity.getCiudad(),
                entity.getLatitud(),
                entity.getLongitud(),
                entity.getHorarioApertura(),
                entity.getHorarioCierre(),
                entity.isActive());
    }

    public static LocalEntity toEntity(Local domain) {
        if (domain == null)
            return null;
        return new LocalEntity(
                domain.id(),
                domain.nombre(),
                domain.direccion(),
                domain.ciudad(),
                domain.latitud(),
                domain.longitud(),
                domain.horarioApertura(),
                domain.horarioCierre(),
                domain.isActive());
    }
}
