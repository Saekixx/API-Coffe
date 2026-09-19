package com.api.covoshcoffe.store.application.services;

import java.util.List;

import com.api.covoshcoffe.store.application.dto.response.LocalDto;
import com.api.covoshcoffe.store.application.ports.GetLocalUseCase;
import com.api.covoshcoffe.store.domain.ports.out.LocalRepositoryPort;

public class GetLocalService implements GetLocalUseCase {
    private final LocalRepositoryPort localRepositoryPort;

    public GetLocalService(LocalRepositoryPort localRepositoryPort) {
        this.localRepositoryPort = localRepositoryPort;
    }

    @Override
    public List<LocalDto> getAllLocales() {
        return localRepositoryPort.findAll().stream()
                .map(local -> new LocalDto(
                        local.id(),
                        local.razonSocial(),
                        local.direccion(),
                        local.distrito().id(),
                        local.horario(),
                        String.valueOf(local.latitud()),
                        String.valueOf(local.longitud())))
                .toList();
    }
}
