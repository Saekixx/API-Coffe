package com.api.covoshcoffe.store.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.covoshcoffe.store.application.dto.response.DistritoDto;
import com.api.covoshcoffe.store.application.ports.GetDistritoUseCase;
import com.api.covoshcoffe.store.domain.ports.out.DistritoRepositoryPort;

@Service
public class GetDistritoService implements GetDistritoUseCase {
    private final DistritoRepositoryPort distritoRepositoryPort;

    public GetDistritoService(DistritoRepositoryPort distritoRepositoryPort) {
        this.distritoRepositoryPort = distritoRepositoryPort;
    }

    @Override
    public List<DistritoDto> getAllDistritos() {
        return distritoRepositoryPort.findAll().stream()
                .map(distrito -> new DistritoDto(
                        distrito.id(),
                        distrito.detalle()))
                .toList();
    }
}
