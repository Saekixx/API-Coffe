package com.api.covoshcoffe.store.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;
import com.api.covoshcoffe.store.application.dto.request.CreateLocalCommand;
import com.api.covoshcoffe.store.application.dto.request.UpdateLocalCommand;
import com.api.covoshcoffe.store.application.ports.ManageStoreUseCase;
import com.api.covoshcoffe.store.domain.model.Local;
import com.api.covoshcoffe.store.domain.ports.out.LocalRepositoryPort;

@Service
public class ManageStoreService implements ManageStoreUseCase {
    private final LocalRepositoryPort localRepositoryPort;

    public ManageStoreService(LocalRepositoryPort localRepositoryPort) {
        this.localRepositoryPort = localRepositoryPort;
    }

    @Override
    public List<Local> getAllStores() {
        return localRepositoryPort.findAll();
    }

    @Override
    public Local createStore(CreateLocalCommand command) {
        if (localRepositoryPort.existsByNombre(command.nombre())) {
            throw new ResourceNotFoundException("Ya existe un local con el nombre: " + command.nombre());
        }

        Local local = new Local(
                null,
                command.nombre(),
                command.direccion(),
                command.ciudad(),
                command.latitud(),
                command.longitud(),
                command.horarioApertura(),
                command.horarioCierre(),
                true // Nuevo local activo por defecto
        );

        return localRepositoryPort.save(local);
    }

    @Override
    public Local updateStore(Integer id, UpdateLocalCommand command) {
        Local existingLocal = localRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local no encontrado con el id: " + id));

        Local updatedLocal = new Local(
                existingLocal.id(),
                command.nombre() != null ? command.nombre() : existingLocal.nombre(),
                command.direccion() != null ? command.direccion() : existingLocal.direccion(),
                command.ciudad() != null ? command.ciudad() : existingLocal.ciudad(),
                command.latitud() != null ? command.latitud() : existingLocal.latitud(),
                command.longitud() != null ? command.longitud() : existingLocal.longitud(),
                command.horarioApertura() != null ? command.horarioApertura() : existingLocal.horarioApertura(),
                command.horarioCierre() != null ? command.horarioCierre() : existingLocal.horarioCierre(),
                existingLocal.isActive());

        return localRepositoryPort.save(updatedLocal);
    }

    @Override
    public void toggleStoreStatus(Integer id) {
        Local local = localRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local no encontrado con el id: " + id));

        Local updatedLocal = new Local(
                local.id(),
                local.nombre(),
                local.direccion(),
                local.ciudad(),
                local.latitud(),
                local.longitud(),
                local.horarioApertura(),
                local.horarioCierre(),
                !local.isActive() // Cambiar el estado activo
        );

        localRepositoryPort.save(updatedLocal);
    }
}
