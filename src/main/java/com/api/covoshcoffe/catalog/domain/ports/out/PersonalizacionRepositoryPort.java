package com.api.covoshcoffe.catalog.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.catalog.domain.model.GrupoPersonalizacion;
import com.api.covoshcoffe.catalog.domain.model.OpcionPersonalizacion;

public interface PersonalizacionRepositoryPort {
    GrupoPersonalizacion saveGroup(GrupoPersonalizacion grupo);

    OpcionPersonalizacion saveOption(OpcionPersonalizacion opcion);

    List<GrupoPersonalizacion> findAllActiveGroups();

    Optional<GrupoPersonalizacion> findGroupById(Integer id);

    Optional<OpcionPersonalizacion> findOptionById(Integer id);
}
