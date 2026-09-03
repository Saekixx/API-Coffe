package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.covoshcoffe.catalog.domain.model.GrupoPersonalizacion;
import com.api.covoshcoffe.catalog.domain.model.OpcionPersonalizacion;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.GrupoPersonalizacionEntity;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.OpcionPersonalizacionEntity;

public class PersonalizacionPersistenceMapper {

    public static GrupoPersonalizacion toDomain(GrupoPersonalizacionEntity entity,
            List<OpcionPersonalizacionEntity> opcionesEntities) {
        if (entity == null)
            return null;

        List<OpcionPersonalizacion> opciones = opcionesEntities != null
                ? opcionesEntities.stream()
                        .map(PersonalizacionPersistenceMapper::toDomain)
                        .toList()
                : Collections.emptyList();

        return new GrupoPersonalizacion(
                entity.getId(),
                entity.getNombre(),
                entity.getEsObligatorio(),
                entity.getMaxSeleccion(),
                entity.isActive(),
                opciones);
    }

    public static GrupoPersonalizacionEntity toEntity(GrupoPersonalizacion domain) {
        if (domain == null)
            return null;

        GrupoPersonalizacionEntity entity = new GrupoPersonalizacionEntity();
        entity.setId(domain.id());
        entity.setNombre(domain.nombre());
        entity.setEsObligatorio(domain.esObligatorio());
        entity.setMaxSeleccion(domain.maxSeleccion());
        entity.setActive(domain.isActive());

        return entity;
    }

    public static OpcionPersonalizacion toDomain(OpcionPersonalizacionEntity entity) {
        if (entity == null)
            return null;

        GrupoPersonalizacion grupoDomain = null;
        if (entity.getGrupoPersonalizacion() != null) {
            GrupoPersonalizacionEntity grupoEntity = entity.getGrupoPersonalizacion();
            grupoDomain = new GrupoPersonalizacion(
                    grupoEntity.getId(),
                    grupoEntity.getNombre(),
                    grupoEntity.getEsObligatorio(),
                    grupoEntity.getMaxSeleccion(),
                    grupoEntity.isActive(),
                    Collections.emptyList());
        }

        return new OpcionPersonalizacion(
                entity.getId(),
                entity.getNombre(),
                entity.getPrecioAdicional(),
                grupoDomain);
    }

    public static OpcionPersonalizacionEntity toEntity(OpcionPersonalizacion domain) {
        if (domain == null)
            return null;

        GrupoPersonalizacionEntity grupoEntity = null;
        if (domain.grupoPersonalizacion() != null) {
            grupoEntity = new GrupoPersonalizacionEntity();
            grupoEntity.setId(domain.grupoPersonalizacion().id());
        }

        return toEntity(domain, grupoEntity);
    }

    public static OpcionPersonalizacionEntity toEntity(OpcionPersonalizacion domain,
            GrupoPersonalizacionEntity grupoEntity) {
        if (domain == null)
            return null;

        OpcionPersonalizacionEntity entity = new OpcionPersonalizacionEntity();
        entity.setId(domain.id());
        entity.setNombre(domain.nombre());
        entity.setPrecioAdicional(domain.precioAdicional());
        entity.setGrupoPersonalizacion(grupoEntity);

        return entity;
    }

    public static Set<OpcionPersonalizacion> toDomainSet(Set<OpcionPersonalizacionEntity> entities) {
        if (entities == null)
            return Collections.emptySet();

        return entities.stream()
                .map(PersonalizacionPersistenceMapper::toDomain)
                .collect(Collectors.toSet());
    }
}