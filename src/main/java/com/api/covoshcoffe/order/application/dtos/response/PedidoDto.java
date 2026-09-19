package com.api.covoshcoffe.order.application.dtos.response;

public record PedidoDto(
        Integer id,
        Integer idUsuario,
        Integer idLocal,
        String fechaVenta,
        Double total,
        Integer items) {

}
