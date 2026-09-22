package com.api.covoshcoffe.catalog.domain.ports.out;

import com.api.covoshcoffe.catalog.domain.dtos.GrupoPersonalizacionDto;

import java.util.List;

public interface GrupoPersonalizacionRepositoryPort {
    List<GrupoPersonalizacionDto> getGrupoPersonalizacion();
}
