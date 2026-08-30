package com.api.covoshcoffe.catalog.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.catalog.domain.model.Categoria;

public interface CategoriaRepositoryPort {
    Categoria save(Categoria categoria);

    Optional<Categoria> findById(Integer id);

    List<Categoria> findAllActive();

    List<Categoria> findAll();

    boolean existsById(Integer id);
}
