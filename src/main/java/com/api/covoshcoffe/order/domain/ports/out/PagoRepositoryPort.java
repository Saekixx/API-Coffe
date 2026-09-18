package com.api.covoshcoffe.order.domain.ports.out;

import com.api.covoshcoffe.order.domain.model.Pagos;

import java.util.List;
import java.util.Optional;

public interface PagoRepositoryPort {
    Pagos save(Pagos pago);

    List<Pagos> findAll();

    Optional<Pagos> findById(Integer id);

    // Búsquedas clave para auditoría y pasarelas de pago
    Optional<Pagos> findByPedidoId(Integer pedidoId);

}
