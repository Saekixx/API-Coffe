package com.api.covoshcoffe.promotion.infrastructure.output.persistence.mapper;

import com.api.covoshcoffe.promotion.domain.model.Cupones;
import com.api.covoshcoffe.promotion.infrastructure.output.persistence.entity.CuponesEntity;

public class CuponesPersistenceMapper {
    public static Cupones toDomain(CuponesEntity domain) {
        if (domain == null)
            return null;
        return new Cupones(
                domain.getId(),
                domain.getCodigo(),
                domain.getDescuento(),
                domain.getLimiteUsos(),
                domain.getUsosActuales(),
                domain.getFechaExpiracion(),
                domain.isActivo());
    }

    public static CuponesEntity toEntity(Cupones entity) {
        if (entity == null)
            return null;
        return new CuponesEntity(
                entity.id(),
                entity.codigo(),
                entity.descuento(),
                entity.limiteUsos(),
                entity.usosActuales(),
                entity.fechaExpiracion(),
                entity.activo());
    }
}
