package com.api.covoshcoffe.order.application.services;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.api.covoshcoffe.order.application.dtos.response.PedidoDto;
import com.api.covoshcoffe.order.application.ports.in.GetPedidoUseCase;
import com.api.covoshcoffe.order.domain.ports.out.PedidoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetPedidoService implements GetPedidoUseCase {
    private final PedidoRepositoryPort pedidoRepositoryPort;
    // Formatea la fecha de entrega
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    public GetPedidoService(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Override
    public List<PedidoDto> getAllPedidos() {
        return pedidoRepositoryPort.findAll().stream()
                .map(pedido -> new PedidoDto(
                        pedido.id(),
                        pedido.usuario().id(),
                        pedido.local().id(),
                        formatter.format(pedido.fechaEntrega()),
                        pedido.total(),
                        pedido.itemsTotal()))
                .toList();
    }
}
