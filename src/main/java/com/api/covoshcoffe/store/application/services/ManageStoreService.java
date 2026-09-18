package com.api.covoshcoffe.store.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;
import com.api.covoshcoffe.store.application.dto.request.CreateLocalCommand;
import com.api.covoshcoffe.store.application.dto.request.UpdateLocalCommand;
import com.api.covoshcoffe.store.application.ports.ManagerStoreUseCase;
import com.api.covoshcoffe.store.domain.model.Distrito;
import com.api.covoshcoffe.store.domain.model.Local;
import com.api.covoshcoffe.store.domain.ports.out.DistritoRepositoryPort;
import com.api.covoshcoffe.store.domain.ports.out.LocalRepositoryPort;

@Service
public class ManageStoreService implements ManagerStoreUseCase {
    private final LocalRepositoryPort localRepositoryPort;
    private final DistritoRepositoryPort distritoRepositoryPort;

    public ManageStoreService(LocalRepositoryPort localRepositoryPort, DistritoRepositoryPort distritoRepositoryPort) {
        this.localRepositoryPort = localRepositoryPort;
        this.distritoRepositoryPort = distritoRepositoryPort;
    }

    @Override
    public List<Local> getAllStores() {
        return localRepositoryPort.findAll();
    }

    @Override
    public Local createStore(CreateLocalCommand command) {
        if (localRepositoryPort.existsByNombre(command.razonSocial())) {
            throw new ResourceNotFoundException("Ya existe un local con el nombre: " + command.razonSocial());
        }

        Distrito distrito = distritoRepositoryPort.findById(command.distritoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Distrito no encontrado con el id: " + command.distritoId()));

        Local local = new Local(
                null,
                command.razonSocial(),
                command.direccion(),
                command.latitud(),
                command.longitud(),
                command.horario(),
                true,
                distrito);

        return localRepositoryPort.save(local);
    }

    @Override
    public Local updateStore(Integer id, UpdateLocalCommand command) {
        Local existingLocal = localRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local no encontrado con el id: " + id));

        Distrito distrito = distritoRepositoryPort.findById(command.distritoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Distrito no encontrado con el id: " + command.distritoId()));

        Local updatedLocal = new Local(
                existingLocal.id(),
                command.razonSocial(),
                command.direccion(),
                command.latitud(),
                command.longitud(),
                command.horario(),
                existingLocal.isActive(),
                distrito);

        return localRepositoryPort.save(updatedLocal);
    }

    @Override
    public String toggleStoreStatus(Integer id) {
        Local local = localRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Local no encontrado con el id: " + id));

        Local updatedLocal = Local.toggleStatus(local);

        localRepositoryPort.save(updatedLocal);

        return updatedLocal.isActive() ? "Local activado" : "Local desactivado";
    }
}
