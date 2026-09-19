package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.store.domain.model.Distrito;
import com.api.covoshcoffe.store.domain.ports.out.DistritoRepositoryPort;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity.DistritoEntity;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.mapper.DistritoPersistenceMapper;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.repository.SpringDataDistritoRepository;

@Component
public class DistritoRepositoryAdapter implements DistritoRepositoryPort {
    private final SpringDataDistritoRepository springDataRepository;

    public DistritoRepositoryAdapter(SpringDataDistritoRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public List<Distrito> findAll() {
        List<DistritoEntity> entities = springDataRepository.findAll();
        return entities.stream()
                .map(DistritoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Distrito> findById(Integer id) {
        return springDataRepository.findById(id)
                .map(DistritoPersistenceMapper::toDomain);
    }

}
