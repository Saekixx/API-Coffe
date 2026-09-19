package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity.DistritoEntity;

public interface SpringDataDistritoRepository extends JpaRepository<DistritoEntity, Integer> {

}
