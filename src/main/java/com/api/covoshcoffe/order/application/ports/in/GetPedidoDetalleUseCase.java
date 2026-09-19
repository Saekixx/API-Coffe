package com.api.covoshcoffe.order.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.order.application.dtos.response.PedidoDetalleDto;

public interface GetPedidoDetalleUseCase {
    List<PedidoDetalleDto> getAllPedidoDetalles();
}
