package com.api.covoshcoffe.order.domain.model;

import java.time.LocalDateTime;

enum MetodoEntrega{
    EN_LOCAL,
    DELIVERY
}

enum EstadoPedido{
    PENDIENTE,
    EN_PREPARACION,
    LISTO,
    EN_CAMINO,
    ENTREGADO,
    CANCELADO
}

public record Pedidos(
        Integer id,
        Integer usuarioId,
        Integer localId,
        Integer cuponId,
        MetodoEntrega metodoEntrega,
        LocalDateTime fechaEntrega,
        Double subTotal,
        Double descuento,
        Double total,
        Integer itemsTotal,
        EstadoPedido estado,
        LocalDateTime createdAt) {

    public Pedidos(Integer usuarioId, Integer localId, Integer cuponId, MetodoEntrega metodoEntrega, LocalDateTime fechaEntrega, Double subTotal, Double descuento, Double total, Integer itemsTotal, EstadoPedido estado) {
        this(null, usuarioId, localId, cuponId, metodoEntrega, fechaEntrega, subTotal, descuento, total, itemsTotal, estado, LocalDateTime.now());
    }
}
