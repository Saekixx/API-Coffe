package com.api.covoshcoffe.order.domain.model;

public record DetallePedidos(
        Integer id,
        Integer pedidoId,
        Integer productoId,
        Integer medidaId,
        Integer cantidad,
        Double precioUnitario,
        Double subTotal) {
    public DetallePedidos(Integer pedidoId, Integer productoId, Integer medidaId, Integer cantidad, Double precioUnitario) {
        this(null, pedidoId, productoId, medidaId, cantidad, precioUnitario, cantidad * precioUnitario);
    }
}
