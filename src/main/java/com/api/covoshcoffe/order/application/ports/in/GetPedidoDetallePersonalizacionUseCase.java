package com.api.covoshcoffe.order.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.order.application.dtos.response.PedidoDetallePersonalizacionDto;

public interface GetPedidoDetallePersonalizacionUseCase {
    List<PedidoDetallePersonalizacionDto> getAllPedidoDetallesPersonalizacion();
}
