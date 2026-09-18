package com.api.covoshcoffe.order.application.ports.in;

import com.api.covoshcoffe.order.domain.model.Pedidos;

import java.util.List;

public interface GetOrdersUseCase {
    List<Pedidos> getAllOrders();

    Pedidos getOrderById(Integer id);

    List<Pedidos> getOrdersByUsuarioId(Integer usuarioId);

    List<Pedidos> getOrdersByLocalId(Integer localId);

    List<Pedidos> getOrdersByLocalIdAndEstado(Integer localId, String estado);

}
