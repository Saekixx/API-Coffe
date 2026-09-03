package com.api.covoshcoffe.promotion.infrastructure.output.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.promotion.infrastructure.output.persistence.entity.CuponesEntity;

public interface SpringDataCuponesRepository extends JpaRepository<CuponesEntity, Integer> {
    List<CuponesEntity> findAllByIsActiveTrue();

    CuponesEntity findByCode(String code);
}
