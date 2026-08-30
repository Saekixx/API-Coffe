package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.catalog.domain.model.Categoria;
import com.api.covoshcoffe.catalog.domain.ports.out.CategoriaRepositoryPort;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.CategoriaEntity;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper.CategoriaPersistenceMapper;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository.SpringDataCategoriaRepository;

@Component
public class CategoriaRepositoryAdapter implements CategoriaRepositoryPort {
    private final SpringDataCategoriaRepository springDataRepository;

    public CategoriaRepositoryAdapter(SpringDataCategoriaRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity entity = CategoriaPersistenceMapper.toEntity(categoria);
        CategoriaEntity saved = springDataRepository.save(entity);
        return CategoriaPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Categoria> findById(Integer id) {
        return springDataRepository.findById(id)
                .map(CategoriaPersistenceMapper::toDomain);
    }

    @Override
    public List<Categoria> findAllActive() {
        return springDataRepository.findByIsActiveTrue()
                .stream()
                .map(CategoriaPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Categoria> findAll() {
        return springDataRepository.findAll()
                .stream()
                .map(CategoriaPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Integer id) {
        return springDataRepository.existsById(id);
    }
}
