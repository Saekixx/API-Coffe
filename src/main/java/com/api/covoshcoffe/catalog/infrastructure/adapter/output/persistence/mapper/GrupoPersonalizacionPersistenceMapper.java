package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper;

import com.api.covoshcoffe.catalog.domain.model.GrupoPersonalizacion;
import com.api.covoshcoffe.catalog.domain.model.OpcionPersonalizacion;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.GrupoPersonalizacionEntity;

import java.util.List;

public class GrupoPersonalizacionPersistenceMapper {

    public static GrupoPersonalizacion toDomain(GrupoPersonalizacionEntity entity) {
        if (entity == null)
            return null;

        // Mapear la colección de OpcionPersonalizacionEntity a OpcionPersonalizacion
        List<OpcionPersonalizacion> opcionesDomain = entity.getOpciones() != null
                ? entity.getOpciones().stream()
                        .map(PersonalizacionPersistenceMapper::toDomain)
                        .toList()
                : List.of();

        return new GrupoPersonalizacion(
                entity.getId(),
                entity.getNombre(),
                entity.getEsObligatorio(),
                entity.getMaxSeleccion(),
                entity.isActive(),
                opcionesDomain);
    }

    public static GrupoPersonalizacionEntity toEntity(GrupoPersonalizacion domain) {
        if (domain == null)
            return null;

        GrupoPersonalizacionEntity entity = new GrupoPersonalizacionEntity();
        entity.setId(domain.id());
        entity.setNombre(domain.nombre());
        entity.setEsObligatorio(domain.esObligatorio());
        entity.setMaxSeleccion(domain.maxSeleccion());

        // Mapear también de regreso si guardas grupos con sus opciones
        if (domain.personalizaciones() != null) {
            entity.setOpciones(domain.personalizaciones().stream()
                    .map(PersonalizacionPersistenceMapper::toEntity)
                    .collect(java.util.stream.Collectors.toSet()));
        }

        return entity;
    }
}