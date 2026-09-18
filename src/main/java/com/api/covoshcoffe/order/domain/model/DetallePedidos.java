package com.api.covoshcoffe.order.domain.model;

import com.api.covoshcoffe.catalog.domain.model.Medida;
import com.api.covoshcoffe.catalog.domain.model.Producto;

public record DetallePedidos(
        Integer id,
        Pedidos pedido,
        Producto producto,
        Medida medida,
        Integer cantidad,
        Double precioUnitario,
        Double subTotal) {
    public DetallePedidos(Pedidos pedido, Producto producto, Medida medida, Integer cantidad, Double precioUnitario) {
        this(null, pedido, producto, medida, cantidad, precioUnitario, precioUnitario * cantidad);
    }
}
