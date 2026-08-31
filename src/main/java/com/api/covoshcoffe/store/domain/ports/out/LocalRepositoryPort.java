package com.api.covoshcoffe.store.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.store.domain.model.Local;

public interface LocalRepositoryPort {
    Local save(Local local); 

    List<Local> findAll();

    List<Local> findAllActive();

    Optional<Local> findById(Integer id);

    boolean existsByNombre(String nombre);

}
