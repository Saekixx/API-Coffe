package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.mapper.UsuarioPersistenceMapper;
import com.api.covoshcoffe.order.domain.model.Pedidos;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity.PedidosEntity;
import com.api.covoshcoffe.promotion.infrastructure.output.persistence.mapper.CuponesPersistenceMapper;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.mapper.LocalPersistenceMapper;

public class PedidosPersistenceMapper {

    public static Pedidos toDomain(PedidosEntity pedidosEntity) {
        if (pedidosEntity == null) {
            return null;
        }

        return new Pedidos(
                pedidosEntity.getId(),
                pedidosEntity.getUsuario() != null ? UsuarioPersistenceMapper.toDomain(pedidosEntity.getUsuario()) : null,
                pedidosEntity.getLocal() != null ? LocalPersistenceMapper.toDomain(pedidosEntity.getLocal()) : null,
                pedidosEntity.getCupones() != null ? CuponesPersistenceMapper.toDomain(pedidosEntity.getCupones()) : null,
                pedidosEntity.getMetodoEntrega() != null ? Pedidos.MetodoEntrega.valueOf(pedidosEntity.getMetodoEntrega().name()) : null,
                pedidosEntity.getFechaEntrega(),
                pedidosEntity.getSubTotal(),
                pedidosEntity.getDescuento(),
                pedidosEntity.getTotal(),
                pedidosEntity.getItemsTotal(),
                pedidosEntity.getEstado() != null ? Pedidos.EstadoPedido.valueOf(pedidosEntity.getEstado().name()) : null,
                pedidosEntity.getCreatedAt()
        );
    }

    public static PedidosEntity toEntity(Pedidos pedidos) {
        if (pedidos == null) {
            return null;
        }

        PedidosEntity entity = new PedidosEntity();
        entity.setId(pedidos.id());
        entity.setUsuario(pedidos.usuario() != null ? UsuarioPersistenceMapper.toEntity(pedidos.usuario()) : null);
        entity.setLocal(pedidos.local() != null ? LocalPersistenceMapper.toEntity(pedidos.local()) : null);
        entity.setCupones(pedidos.cupones() != null ? CuponesPersistenceMapper.toEntity(pedidos.cupones()) : null);

        if (pedidos.metodoEntrega() != null) {
            entity.setMetodoEntrega(PedidosEntity.MetodoEntrega.valueOf(pedidos.metodoEntrega().name()));
        }

        entity.setFechaEntrega(pedidos.fechaEntrega());
        entity.setSubTotal(pedidos.subTotal());
        entity.setDescuento(pedidos.descuento());
        entity.setTotal(pedidos.total());
        entity.setItemsTotal(pedidos.itemsTotal());

        if (pedidos.estado() != null) {
            entity.setEstado(PedidosEntity.EstadoPedido.valueOf(pedidos.estado().name()));
        }

        entity.setCreatedAt(pedidos.createdAt());

        return entity;
    }
}