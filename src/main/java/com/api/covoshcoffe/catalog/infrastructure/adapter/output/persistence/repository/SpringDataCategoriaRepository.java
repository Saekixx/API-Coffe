package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.CategoriaEntity;

public interface SpringDataCategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    List<CategoriaEntity> findByIsActiveTrue();
}
