package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.catalog.domain.model.Producto;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.ProductoEntity;

public class ProductoPersistenceMapper {
    public static Producto toDomain(ProductoEntity entity) {
        if (entity == null)
            return null;
        return new Producto(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getPrecioBase(),
                CategoriaPersistenceMapper.toDomain(entity.getCategoria()),
                entity.getImagenUrl(),
                entity.isActive());
    }

    public static ProductoEntity toEntity(Producto domain) {
        if (domain == null)
            return null;
        return new ProductoEntity(
                domain.id(),
                domain.nombre(),
                domain.descripcion(),
                domain.precioBase(),
                domain.imagenUrl(),
                domain.isActive(),
                CategoriaPersistenceMapper.toEntity(domain.categoria()));
    }
}
