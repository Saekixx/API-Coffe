package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.OpcionPersonalizacionEntity;
import com.api.covoshcoffe.order.domain.model.DetallePersonalizaciones;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity.DetallePersonalizacionesEntity;

public class DetallePersonalizacionesPersistenceMapper {

    public static DetallePersonalizaciones toDomain(DetallePersonalizacionesEntity entity) {
        if (entity == null) {
            return null;
        }

        return new DetallePersonalizaciones(
                entity.getId(),
                entity.getDetallePedido() != null ? DetallePedidosPersistenceMapper.toDomain(entity.getDetallePedido()) : null,
                entity.getOpcion() != null ? entity.getOpcion().getId() : null
        );
    }

    public static DetallePersonalizacionesEntity toEntity(DetallePersonalizaciones domain) {
        if (domain == null) {
            return null;
        }

        DetallePersonalizacionesEntity entity = new DetallePersonalizacionesEntity();
        entity.setId(domain.id());
        entity.setDetallePedido(domain.detallePedidos() != null ? DetallePedidosPersistenceMapper.toEntity(domain.detallePedidos()) : null);

        if (domain.opcionId() != null) {
            OpcionPersonalizacionEntity opcionEntity = new OpcionPersonalizacionEntity();
            opcionEntity.setId(domain.opcionId());
            entity.setOpcion(opcionEntity);
        }

        return entity;
    }
}