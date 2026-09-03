package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.ProductoEntity;

public interface SpringDataProductoRepository extends JpaRepository<ProductoEntity, Integer> {
    List<ProductoEntity> findByIsActiveTrue();

    List<ProductoEntity> findByCategoriaIdAndIsActiveTrue(Integer categoriaId);

    @Query("""
                SELECT DISTINCT p
                FROM ProductoEntity p
                LEFT JOIN FETCH p.grupos g
                LEFT JOIN FETCH g.opciones
                WHERE p.id = :id
            """)
    Optional<ProductoEntity> findByIdWithGruposAndOpciones(@Param("id") Integer id);
}
