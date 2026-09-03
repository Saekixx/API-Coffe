package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.covoshcoffe.catalog.domain.model.GrupoPersonalizacion;
import com.api.covoshcoffe.catalog.domain.model.Producto;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.GrupoPersonalizacionEntity;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.ProductoEntity;

public class ProductoPersistenceMapper {
    public static Producto toDomain(ProductoEntity entity) {
        if (entity == null)
            return null;

        Set<GrupoPersonalizacion> grupos = (entity.getGrupos() == null)
                ? Collections.emptySet()
                : entity.getGrupos().stream()
                        .map(GrupoPersonalizacionPersistenceMapper::toDomain)
                        .collect(Collectors.toSet());

        return new Producto(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecioBase(),
                CategoriaPersistenceMapper.toDomain(entity.getCategoria()),
                entity.getImagenUrl(),
                entity.isActive(),
                grupos);
    }

    public static ProductoEntity toEntity(Producto domain) {
        if (domain == null)
            return null;

        Set<GrupoPersonalizacionEntity> gruposEntities = (domain.grupos() == null)
                ? Collections.emptySet()
                : domain.grupos().stream()
                        .map(GrupoPersonalizacionPersistenceMapper::toEntity)
                        .collect(Collectors.toSet());

        return new ProductoEntity(
                domain.id(),
                domain.nombre(),
                domain.descripcion(),
                domain.precioBase(),
                domain.imagenUrl(),
                domain.isActive(),
                CategoriaPersistenceMapper.toEntity(domain.categoria()),
                gruposEntities);
    }
}
