package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.covoshcoffe.catalog.domain.model.GrupoPersonalizacion;
import com.api.covoshcoffe.catalog.domain.model.OpcionPersonalizacion;
import com.api.covoshcoffe.catalog.domain.ports.out.PersonalizacionRepositoryPort;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.GrupoPersonalizacionEntity;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.OpcionPersonalizacionEntity;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.mapper.PersonalizacionPersistenceMapper;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository.SpringDataGrupoPersonalizacionRepository;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.repository.SpringDataOpcionPersonalizacionRepository;

@Component
public class PersonalizacionRepositoryAdapter implements PersonalizacionRepositoryPort {
    private final SpringDataGrupoPersonalizacionRepository grupoRepository;
    private final SpringDataOpcionPersonalizacionRepository opcionRepository;

    public PersonalizacionRepositoryAdapter(SpringDataGrupoPersonalizacionRepository grupoRepository,
            SpringDataOpcionPersonalizacionRepository opcionRepository) {
        this.grupoRepository = grupoRepository;
        this.opcionRepository = opcionRepository;
    }

    @Override
    public GrupoPersonalizacion saveGroup(GrupoPersonalizacion grupo) {
        GrupoPersonalizacionEntity entity = PersonalizacionPersistenceMapper.toEntity(grupo);
        GrupoPersonalizacionEntity saved = grupoRepository.save(entity);
        return PersonalizacionPersistenceMapper.toDomain(saved, Collections.emptyList());
    }

    @Override
    public OpcionPersonalizacion saveOption(OpcionPersonalizacion opcion) {
        OpcionPersonalizacionEntity entity = PersonalizacionPersistenceMapper.toEntity(opcion);
        OpcionPersonalizacionEntity saved = opcionRepository.save(entity);
        return PersonalizacionPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<GrupoPersonalizacion> findAllActiveGroups() {
        return grupoRepository.findByIsActiveTrue()
                .stream()
                .map(entity -> PersonalizacionPersistenceMapper.toDomain(entity, Collections.emptyList()))
                .toList();
    }

    @Override
    public Set<GrupoPersonalizacion> findByIds(List<Integer> ids) {
        return grupoRepository.findAllById(ids)
                .stream()
                .map(entity -> PersonalizacionPersistenceMapper.toDomain(entity, Collections.emptyList()))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<GrupoPersonalizacion> findGroupById(Integer id) {
        return grupoRepository.findById(id)
                .map(entity -> PersonalizacionPersistenceMapper.toDomain(entity, Collections.emptyList()));
    }

    @Override
    public Optional<OpcionPersonalizacion> findOptionById(Integer id) {
        return opcionRepository.findById(id)
                .map(PersonalizacionPersistenceMapper::toDomain);
    }
}
