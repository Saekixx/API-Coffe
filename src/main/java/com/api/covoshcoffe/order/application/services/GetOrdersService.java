package com.api.covoshcoffe.order.application.services;

import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;
import com.api.covoshcoffe.order.application.ports.in.GetOrdersUseCase;
import com.api.covoshcoffe.order.domain.model.Pedidos;
import com.api.covoshcoffe.order.domain.ports.out.PedidoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetOrdersService implements GetOrdersUseCase {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    public GetOrdersService(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Override
    public List<Pedidos> getAllOrders() {
        List<Pedidos> pedidos = pedidoRepositoryPort.findAll();
        if (pedidos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pedidos registrados.");
        }
        return pedidos;
    }

    @Override
    public Pedidos getOrderById(Integer id) {
        return pedidoRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con el id: " + id));
    }

    @Override
    public List<Pedidos> getOrdersByUsuarioId(Integer usuarioId) {
        List<Pedidos> pedidos = pedidoRepositoryPort.findByUsuarioId(usuarioId);
        if (pedidos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pedidos para el usuario con id: " + usuarioId);
        }
        return pedidos;
    }

    @Override
    public List<Pedidos> getOrdersByLocalId(Integer localId) {
        List<Pedidos> pedidos = pedidoRepositoryPort.findByLocalId(localId);
        if (pedidos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pedidos para el local con id: " + localId);
        }
        return pedidos;
    }

    @Override
    public List<Pedidos> getOrdersByLocalIdAndEstado(Integer localId, String estado) {
        List<Pedidos> pedidos = pedidoRepositoryPort.findByLocalIdAndEstado(localId, estado);
        if (pedidos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pedidos para el local id: "
                    + localId + " con el estado: " + estado);
        }
        return pedidos;
    }
}