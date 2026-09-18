package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.order.domain.model.Pagos;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity.PagosEntity;

public class PagosPersistenceMapper {

    public static Pagos toDomain(PagosEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Pagos(
                entity.getId(),
                entity.getPedido() != null ? PedidosPersistenceMapper.toDomain(entity.getPedido()) : null,
                entity.getMetodo() != null ? Pagos.MetodosPago.valueOf(entity.getMetodo().name()) : null,
                entity.getProveedor(),
                entity.getUltimosDigitos(),
                entity.getMonto(),
                entity.getEstado() != null ? Pagos.EstadoPago.valueOf(entity.getEstado().name()) : null,
                entity.getPagadoEn()
        );
    }

    public static PagosEntity toEntity(Pagos domain) {
        if (domain == null) {
            return null;
        }

        PagosEntity entity = new PagosEntity();
        entity.setId(domain.id());
        entity.setPedido(domain.pedido() != null ? PedidosPersistenceMapper.toEntity(domain.pedido()) : null);

        if (domain.metodo() != null) {
            entity.setMetodo(PagosEntity.MetodosPago.valueOf(domain.metodo().name()));
        }

        entity.setProveedor(domain.proveedor());
        entity.setUltimosDigitos(domain.ultimosDigitos());
        entity.setMonto(domain.monto());

        if (domain.estado() != null) {
            entity.setEstado(PagosEntity.EstadoPago.valueOf(domain.estado().name()));
        }

        entity.setPagadoEn(domain.pagadoEn());

        return entity;
    }
}