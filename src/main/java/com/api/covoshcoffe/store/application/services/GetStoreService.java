package com.api.covoshcoffe.store.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;
import com.api.covoshcoffe.store.application.ports.GetStoreUseCase;
import com.api.covoshcoffe.store.domain.model.Local;
import com.api.covoshcoffe.store.domain.ports.out.LocalRepositoryPort;

@Service
public class GetStoreService implements GetStoreUseCase {
    private final LocalRepositoryPort localRepositoryPort;

    public GetStoreService(LocalRepositoryPort localRepositoryPort) {
        this.localRepositoryPort = localRepositoryPort;
    }

    @Override
    public List<Local> getAllActiveStores() {
        return localRepositoryPort.findAllActive();
    }

    @Override
    public Local getStoreById(Integer id) {
        return localRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local no encontrado con el id: " + id));
    }
}
