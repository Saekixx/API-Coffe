package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository;

import com.api.covoshcoffe.catalog.domain.dtos.OpcionPersonalizacionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.OpcionPersonalizacionEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataOpcionPersonalizacionRepository extends JpaRepository<OpcionPersonalizacionEntity, Integer> {
    @Query("""
        SELECT new com.api.covoshcoffe.catalog.domain.dtos.OpcionPersonalizacionDto(
            o.id,
            o.grupoPersonalizacion.id,
            o.nombre,
            o.precioAdicional
        )
        FROM OpcionPersonalizacionEntity o
    """)
    List<OpcionPersonalizacionDto> findAllOpcionesDto();
}
