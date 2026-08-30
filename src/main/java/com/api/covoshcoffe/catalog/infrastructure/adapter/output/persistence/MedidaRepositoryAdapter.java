package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.catalog.domain.model.Medida;
import com.api.covoshcoffe.catalog.domain.ports.out.MedidaRepositoryPort;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.MedidaEntity;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper.MedidaPersistenceMapper;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository.SpringDataMedidaRepository;

@Component
public class MedidaRepositoryAdapter implements MedidaRepositoryPort {
    private final SpringDataMedidaRepository springDataRepository;

    public MedidaRepositoryAdapter(SpringDataMedidaRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Medida save(Medida medida) {
        MedidaEntity entity = MedidaPersistenceMapper.toEntity(medida);
        MedidaEntity saved = springDataRepository.save(entity);
        return MedidaPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Medida> findById(Integer id) {
        return springDataRepository.findById(id)
                .map(MedidaPersistenceMapper::toDomain);
    }

    @Override
    public List<Medida> findAllActive() {
        return springDataRepository.findByIsActiveTrue()
                .stream()
                .map(MedidaPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Medida> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(MedidaPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Integer id) {
        return springDataRepository.existsById(id);
    }
}
