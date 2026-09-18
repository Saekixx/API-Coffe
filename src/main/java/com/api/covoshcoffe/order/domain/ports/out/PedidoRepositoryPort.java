package com.api.covoshcoffe.order.domain.ports.out;

import com.api.covoshcoffe.order.domain.model.Pedidos;

import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
    Pedidos save(Pedidos pedido);

    List<Pedidos> findAll();

    Optional<Pedidos> findById(Integer id);

    // Consultas clave para el negocio
    List<Pedidos> findByUsuarioId(Integer usuarioId);

    List<Pedidos> findByLocalIdAndEstado(Integer localId, String estado);

    List<Pedidos> findByLocalId(Integer localId);
}
