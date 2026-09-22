package com.api.covoshcoffe.catalog.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.catalog.application.dto.response.CategoriaDto;
import com.api.covoshcoffe.catalog.application.dto.response.ProductoDto;
import com.api.covoshcoffe.catalog.domain.dtos.GrupoPersonalizacionDto;
import com.api.covoshcoffe.catalog.domain.dtos.OpcionPersonalizacionDto;
import com.api.covoshcoffe.catalog.domain.dtos.ProductoGrupoDto;
import com.api.covoshcoffe.catalog.domain.dtos.ProductsWithFavoritesDto;

public interface GetProductoUseCase {
    List<ProductoDto> getAllProductos();

    List<CategoriaDto> getAllCategorias();

    List<ProductsWithFavoritesDto> findAllWithFavorites(Integer usuarioId);

    List<GrupoPersonalizacionDto> getGrupoPersonalizacion();

    List<OpcionPersonalizacionDto> getOpcionPersonalizacion();

    List<ProductoGrupoDto> findAllWithGruposAndOpciones();
}
