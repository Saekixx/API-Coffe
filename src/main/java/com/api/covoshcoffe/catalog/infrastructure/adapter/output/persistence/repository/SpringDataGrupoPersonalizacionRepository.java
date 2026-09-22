package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository;

import java.util.List;

import com.api.covoshcoffe.catalog.domain.dtos.GrupoPersonalizacionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.GrupoPersonalizacionEntity;
import org.springframework.data.jpa.repository.Query;

public interface SpringDataGrupoPersonalizacionRepository extends JpaRepository<GrupoPersonalizacionEntity, Integer> {
    List<GrupoPersonalizacionEntity> findByIsActiveTrue();

    @Query("""
        SELECT DISTINCT new com.api.covoshcoffe.catalog.domain.dtos.GrupoPersonalizacionDto(
            g.id,
            g.nombre,
            CASE WHEN g.esObligatorio = true THEN 1 ELSE 0 END,
            g.maxSeleccion
        )
        FROM GrupoPersonalizacionEntity g
        WHERE g.isActive = true
    """)
    List<GrupoPersonalizacionDto> findAllGruposDto();
}
