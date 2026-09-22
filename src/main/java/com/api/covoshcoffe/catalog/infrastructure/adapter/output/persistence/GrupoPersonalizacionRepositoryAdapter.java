package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence;

import com.api.covoshcoffe.catalog.domain.dtos.GrupoPersonalizacionDto;
import com.api.covoshcoffe.catalog.domain.ports.out.GrupoPersonalizacionRepositoryPort;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository.SpringDataGrupoPersonalizacionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrupoPersonalizacionRepositoryAdapter implements GrupoPersonalizacionRepositoryPort {
    private final SpringDataGrupoPersonalizacionRepository springDataRepository;

    public GrupoPersonalizacionRepositoryAdapter(SpringDataGrupoPersonalizacionRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public List<GrupoPersonalizacionDto> getGrupoPersonalizacion() {
        return springDataRepository.findAllGruposDto();
    }
}
