package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.catalog.domain.model.Medida;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.MedidaEntity;

public class MedidaPersistenceMapper {
    public static Medida toDomain(MedidaEntity entity) {
        if (entity == null)
            return null;
        return new Medida(
                entity.getId(),
                entity.getNombre(),
                entity.getVolumenMl(),
                entity.getPrecioAdicional(),
                entity.isActive());
    }

    public static MedidaEntity toEntity(Medida domain) {
        if (domain == null)
            return null;
        return new MedidaEntity(
                domain.id(),
                domain.nombre(),
                domain.volumenMl(),
                domain.precioAdicional(),
                domain.isActive());
    }
}
