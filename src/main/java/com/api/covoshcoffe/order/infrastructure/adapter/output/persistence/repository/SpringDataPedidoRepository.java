package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.repository;

import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataPedidoRepository extends JpaRepository<PedidosEntity, Integer> {
    List<PedidosEntity> findByUsuarioId(Integer usuarioId);

    List<PedidosEntity> findByLocalId(Integer localId);

    List<PedidosEntity> findByLocalIdAndEstado(Integer localId, PedidosEntity.EstadoPedido estado);
}
