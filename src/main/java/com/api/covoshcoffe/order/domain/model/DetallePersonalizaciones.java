package com.api.covoshcoffe.order.domain.model;

public record DetallePersonalizaciones(
        Integer id,
        DetallePedidos detallePedidos,
        Integer opcionId
) {
    public DetallePersonalizaciones(DetallePedidos detallePedidos, Integer opcionId) {
        this(null, detallePedidos, opcionId);
    }
}
