package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.store.domain.model.Local;
import com.api.covoshcoffe.store.domain.ports.out.LocalRepositoryPort;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity.LocalEntity;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.mapper.LocalPersistenceMapper;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.repository.SpringDataLocalRepository;

@Component
public class LocalRepositoryAdapter implements LocalRepositoryPort {
    private final SpringDataLocalRepository springDataRepository;

    public LocalRepositoryAdapter(SpringDataLocalRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Local save(Local local) {
        LocalEntity entity = LocalPersistenceMapper.toEntity(local);

        LocalEntity saved = springDataRepository.save(entity);

        return LocalPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Local> findAll() {
        List<LocalEntity> entities = springDataRepository.findAll();
        return entities.stream()
                .map(LocalPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Local> findAllActive() {
        List<LocalEntity> entities = springDataRepository.findAllByIsActiveTrue();
        return entities.stream()
                .map(LocalPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Local> findById(Integer id) {
        return springDataRepository.findById(id)
                .map(LocalPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return springDataRepository.existsByNombre(nombre);
    }

}
