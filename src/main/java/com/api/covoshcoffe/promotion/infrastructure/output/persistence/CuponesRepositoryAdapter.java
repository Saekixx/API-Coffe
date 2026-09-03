package com.api.covoshcoffe.promotion.infrastructure.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.promotion.domain.model.Cupones;
import com.api.covoshcoffe.promotion.domain.ports.out.CategoryRepositoryPort;
import com.api.covoshcoffe.promotion.infrastructure.output.persistence.entity.CuponesEntity;
import com.api.covoshcoffe.promotion.infrastructure.output.persistence.mapper.CuponesPersistenceMapper;
import com.api.covoshcoffe.promotion.infrastructure.output.persistence.repository.SpringDataCuponesRepository;

@Component
public class CuponesRepositoryAdapter implements CategoryRepositoryPort {
    private final SpringDataCuponesRepository springDataRepository;

    public CuponesRepositoryAdapter(SpringDataCuponesRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public List<Cupones> findAll() {
        List<CuponesEntity> entities = springDataRepository.findAll();
        return entities.stream()
                .map(CuponesPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Cupones> findAllActive() {
        List<CuponesEntity> entities = springDataRepository.findAllByActivoTrue();
        return entities.stream()
                .map(CuponesPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Cupones save(Cupones cupones) {
        CuponesEntity entity = CuponesPersistenceMapper.toEntity(cupones);

        CuponesEntity saved = springDataRepository.save(entity);

        return CuponesPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Cupones> findById(Integer id) {
        return springDataRepository.findById(id)
                .map(CuponesPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Cupones> findByCode(String code) {
        return Optional.ofNullable(springDataRepository.findByCodigo(code))
                .map(CuponesPersistenceMapper::toDomain);
    }

}
