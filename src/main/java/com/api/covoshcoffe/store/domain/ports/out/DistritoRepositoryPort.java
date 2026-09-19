package com.api.covoshcoffe.store.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.store.domain.model.Distrito;

public interface DistritoRepositoryPort {
    List<Distrito> findAll();

    Optional<Distrito> findById(Integer id);

}