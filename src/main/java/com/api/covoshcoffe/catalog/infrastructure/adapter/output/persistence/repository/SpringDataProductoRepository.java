package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.catalog.domain.dtos.ProductoGrupoDto;
import com.api.covoshcoffe.catalog.domain.dtos.ProductsWithFavoritesDto;
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

    @Query(value = """
        SELECT 
            p.id AS id,
            p.nombre AS detalle,
            p.descripcion AS descripcion,
            p.precio_base AS precio,
            p.categoria_id AS categoria,
            CASE WHEN p.is_nuevo = 1 THEN 1 ELSE 0 END AS nuevo,
            CASE WHEN p.is_frecuente = 1 THEN 1 ELSE 0 END AS frecuente,
            CASE WHEN f.producto_id IS NOT NULL THEN 1 ELSE 0 END AS favorito
        FROM productos p
        LEFT JOIN favoritos f ON p.id = f.producto_id AND f.usuario_id = ?1
        WHERE p.is_active = 1
    """, nativeQuery = true)
    List<ProductsWithFavoritesDto> findAllWithFavoritesByUsuarioIdNative(Integer usuarioId);

    @Query("""
        SELECT DISTINCT new com.api.covoshcoffe.catalog.domain.dtos.ProductoGrupoDto(
            p.id,
            g.id
        )
        FROM ProductoEntity p
        JOIN p.grupos g
        WHERE p.isActive = true AND g.isActive = true
    """)
    List<ProductoGrupoDto> findAllProductoGruposDto();
}
