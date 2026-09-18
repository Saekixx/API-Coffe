package com.api.covoshcoffe.order.domain.model;

import com.api.covoshcoffe.auth.domain.model.Usuario;
import com.api.covoshcoffe.promotion.domain.model.Cupones;
import com.api.covoshcoffe.store.domain.model.Local;

import java.time.LocalDateTime;

public record Pedidos(
        Integer id,
        Usuario usuario,
        Local local,
        Cupones cupones,
        MetodoEntrega metodoEntrega,
        LocalDateTime fechaEntrega,
        Double subTotal,
        Double descuento,
        Double total,
        Integer itemsTotal,
        EstadoPedido estado,
        LocalDateTime createdAt) {

    public Pedidos(Usuario usuario, Local local, Cupones cupones, MetodoEntrega metodoEntrega, LocalDateTime fechaEntrega, Double subTotal, Double descuento, Double total, Integer itemsTotal, EstadoPedido estado) {
        this(null, usuario, local, cupones, metodoEntrega, fechaEntrega, subTotal, descuento, total, itemsTotal, estado, LocalDateTime.now());
    }

    public enum MetodoEntrega{
        EN_LOCAL,
        DELIVERY
    }

    public enum EstadoPedido{
        PENDIENTE,
        EN_PREPARACION,
        LISTO,
        EN_CAMINO,
        ENTREGADO,
        CANCELADO
    }
}
