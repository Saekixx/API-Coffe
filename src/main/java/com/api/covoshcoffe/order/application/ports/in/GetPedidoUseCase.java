package com.api.covoshcoffe.order.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.order.application.dtos.response.PedidoDto;

public interface GetPedidoUseCase {
    List<PedidoDto> getAllPedidos();
}
