package com.api.covoshcoffe.order.application.dtos.response;

public record PedidoDetalleDto(
        Integer id,
        Integer idPedido,
        Integer idProducto,
        Integer cantidad,
        Double total) {

}
