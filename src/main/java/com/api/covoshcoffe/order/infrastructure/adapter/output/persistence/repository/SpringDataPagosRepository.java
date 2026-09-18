package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.repository;

import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity.PagosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataPagosRepository extends JpaRepository<PagosEntity, Integer> {
    Optional<PagosEntity> findByPedidoId(Integer pedidoId);

}
