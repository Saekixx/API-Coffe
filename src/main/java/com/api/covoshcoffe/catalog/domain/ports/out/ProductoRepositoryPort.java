package com.api.covoshcoffe.catalog.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.catalog.domain.model.Producto;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);

    Optional<Producto> findById(Integer id);

    List<Producto> findAll();

    List<Producto> findAllActive();

    List<Producto> findByCategoryId(Integer categoryId);

    boolean existsById(Integer id);
}
