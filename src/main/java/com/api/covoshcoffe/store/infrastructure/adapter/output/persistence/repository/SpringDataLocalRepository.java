package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity.LocalEntity;

public interface SpringDataLocalRepository extends JpaRepository<LocalEntity, Integer> {
    List<LocalEntity> findAllByIsActiveTrue();

    boolean existsByNombre(String nombre);
}
