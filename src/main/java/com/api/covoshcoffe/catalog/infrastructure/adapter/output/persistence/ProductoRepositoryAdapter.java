package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.catalog.domain.model.Producto;
import com.api.covoshcoffe.catalog.domain.ports.out.ProductoRepositoryPort;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.ProductoEntity;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper.ProductoPersistenceMapper;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository.SpringDataProductoRepository;

@Component
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {
    private final SpringDataProductoRepository springDataRepository;

    public ProductoRepositoryAdapter(SpringDataProductoRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = ProductoPersistenceMapper.toEntity(producto);
        ProductoEntity saved = springDataRepository.save(entity);
        return ProductoPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return springDataRepository.findById(id)
                .map(ProductoPersistenceMapper::toDomain);
    }

    @Override
    public List<Producto> findAllActive() {
        return springDataRepository.findByIsActiveTrue()
                .stream()
                .map(ProductoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Producto> findByCategoryId(Integer categoryId) {
        return springDataRepository.findByCategoriaIdAndIsActiveTrue(categoryId)
                .stream()
                .map(ProductoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Integer id) {
        return springDataRepository.existsById(id);
    }

}
