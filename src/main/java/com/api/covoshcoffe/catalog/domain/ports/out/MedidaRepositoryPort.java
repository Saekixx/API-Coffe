package com.api.covoshcoffe.catalog.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.catalog.domain.model.Medida;

public interface MedidaRepositoryPort {
    Medida save(Medida medida);

    Optional<Medida> findById(Integer id);

    List<Medida> findAllActive();

    List<Medida> findAll();

    boolean existsById(Integer id);
}
