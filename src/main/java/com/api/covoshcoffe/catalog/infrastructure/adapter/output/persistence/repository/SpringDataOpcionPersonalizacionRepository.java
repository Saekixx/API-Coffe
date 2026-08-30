package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.OpcionPersonalizacionEntity;

public interface SpringDataOpcionPersonalizacionRepository extends JpaRepository<OpcionPersonalizacionEntity, Integer> {
}
