package com.api.covoshcoffe.catalog.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.catalog.domain.dtos.ProductoGrupoDto;
import com.api.covoshcoffe.catalog.domain.dtos.ProductsWithFavoritesDto;
import com.api.covoshcoffe.catalog.domain.model.Producto;
import org.springframework.security.core.parameters.P;

public interface ProductoRepositoryPort {
    Producto save(Producto producto);

    Optional<Producto> findById(Integer id);

    List<Producto> findAll();

    List<Producto> findAllActive();

    List<ProductsWithFavoritesDto> findAllWithFavorites(Integer usuarioId);

    List<Producto> findByCategoryId(Integer categoryId);

    List<ProductoGrupoDto> findAllWithGruposAndOpciones();

    boolean existsById(Integer id);
}
