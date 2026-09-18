package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence;

import com.api.covoshcoffe.order.domain.model.Pagos;
import com.api.covoshcoffe.order.domain.ports.out.PagoRepositoryPort;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity.PagosEntity;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.mapper.PagosPersistenceMapper;
import com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.repository.SpringDataPagosRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PagoRepositoryAdapter implements PagoRepositoryPort {

    private final SpringDataPagosRepository springDataPagosRepository;

    public PagoRepositoryAdapter(SpringDataPagosRepository springDataPagosRepository) {
        this.springDataPagosRepository = springDataPagosRepository;
    }

    @Override
    public Pagos save(Pagos pago) {
        PagosEntity entity = PagosPersistenceMapper.toEntity(pago);
        PagosEntity savedEntity = springDataPagosRepository.save(entity);
        return PagosPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public List<Pagos> findAll() {
        return springDataPagosRepository.findAll().stream()
                .map(PagosPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Pagos> findById(Integer id) {
        return springDataPagosRepository.findById(id)
                .map(PagosPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Pagos> findByPedidoId(Integer pedidoId) {
        return springDataPagosRepository.findByPedidoId(pedidoId)
                .map(PagosPersistenceMapper::toDomain);
    }
}