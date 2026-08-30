package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.ProductoEntity;

public interface SpringDataProductoRepository extends JpaRepository<ProductoEntity, Integer> {
    List<ProductoEntity> findByIsActiveTrue();

    List<ProductoEntity> findByCategoriaIdAndIsActiveTrue(Integer categoriaId);
}
