package com.api.covoshcoffe.order.domain.model;

public record DetallePersonalizaciones(
        Integer id,
        Integer detallePedidoId,
        Integer opcionId
) {
    public DetallePersonalizaciones(Integer detallePedidoId, Integer opcionId) {
        this(null, detallePedidoId, opcionId);
    }
}
