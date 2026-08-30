package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.GrupoPersonalizacionEntity;

public interface SpringDataGrupoPersonalizacionRepository extends JpaRepository<GrupoPersonalizacionEntity, Integer> {
    List<GrupoPersonalizacionEntity> findByIsActiveTrue();
}
