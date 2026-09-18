package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence;

import com.api.covoshcoffe.order.domain.model.Pedidos;
import com.api.covoshcoffe.order.domain.ports.out.PedidoRepositoryPort;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity.PedidosEntity;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.mapper.PedidosPersistenceMapper;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.repository.SpringDataPedidoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final SpringDataPedidoRepository springDataPedidoRepository;

    public PedidoRepositoryAdapter(SpringDataPedidoRepository springDataPedidoRepository) {
        this.springDataPedidoRepository = springDataPedidoRepository;
    }

    @Override
    public Pedidos save(Pedidos pedido) {
        PedidosEntity entity = PedidosPersistenceMapper.toEntity(pedido);
        PedidosEntity savedEntity = springDataPedidoRepository.save(entity);
        return PedidosPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public List<Pedidos> findAll() {
        return springDataPedidoRepository.findAll().stream()
                .map(PedidosPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Pedidos> findById(Integer id) {
        return springDataPedidoRepository.findById(id)
                .map(PedidosPersistenceMapper::toDomain);
    }

    @Override
    public List<Pedidos> findByUsuarioId(Integer usuarioId) {
        return springDataPedidoRepository.findByUsuarioId(usuarioId).stream()
                .map(PedidosPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Pedidos> findByLocalIdAndEstado(Integer localId, String estado) {
        // Conversión del String a Enum de persistencia para consultar a Spring Data JPA
        PedidosEntity.EstadoPedido estadoEnum = PedidosEntity.EstadoPedido.valueOf(estado.toUpperCase());

        return springDataPedidoRepository.findByLocalIdAndEstado(localId, estadoEnum).stream()
                .map(PedidosPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Pedidos> findByLocalId(Integer localId) {
        return springDataPedidoRepository.findByLocalId(localId).stream()
                .map(PedidosPersistenceMapper::toDomain)
                .toList();
    }
}