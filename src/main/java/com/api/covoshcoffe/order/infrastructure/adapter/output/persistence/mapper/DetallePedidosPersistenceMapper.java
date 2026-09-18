package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper.MedidaPersistenceMapper;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper.ProductoPersistenceMapper;
import com.api.covoshcoffe.order.domain.model.DetallePedidos;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity.DetallePedidosEntity;

public class DetallePedidosPersistenceMapper {

    public static DetallePedidos toDomain(DetallePedidosEntity entity) {
        if (entity == null) {
            return null;
        }

        return new DetallePedidos(
                entity.getId(),
                entity.getPedido() != null ? PedidosPersistenceMapper.toDomain(entity.getPedido()) : null,
                entity.getProducto() != null ? ProductoPersistenceMapper.toDomain(entity.getProducto()) : null,
                entity.getMedida() != null ? MedidaPersistenceMapper.toDomain(entity.getMedida()) : null,
                entity.getCantidad(),
                entity.getPrecioUnitario(),
                entity.getSubTotal()
        );
    }

    public static DetallePedidosEntity toEntity(DetallePedidos domain) {
        if (domain == null) {
            return null;
        }

        DetallePedidosEntity entity = new DetallePedidosEntity();
        entity.setId(domain.id());
        entity.setPedido(domain.pedido() != null ? PedidosPersistenceMapper.toEntity(domain.pedido()) : null);
        entity.setProducto(domain.producto() != null ? ProductoPersistenceMapper.toEntity(domain.producto()) : null);
        entity.setMedida(domain.medida() != null ? MedidaPersistenceMapper.toEntity(domain.medida()) : null);
        entity.setCantidad(domain.cantidad());
        entity.setPrecioUnitario(domain.precioUnitario());

        return entity;
    }
}