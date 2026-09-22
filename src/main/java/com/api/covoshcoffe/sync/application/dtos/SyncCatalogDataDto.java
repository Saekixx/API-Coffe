package com.api.covoshcoffe.sync.application.dtos;

import com.api.covoshcoffe.catalog.application.dto.response.CategoriaDto;
import com.api.covoshcoffe.catalog.domain.dtos.GrupoPersonalizacionDto;
import com.api.covoshcoffe.catalog.domain.dtos.OpcionPersonalizacionDto;
import com.api.covoshcoffe.catalog.domain.dtos.ProductoGrupoDto;
import com.api.covoshcoffe.catalog.domain.dtos.ProductsWithFavoritesDto;
import com.api.covoshcoffe.store.application.dto.response.DistritoDto;
import com.api.covoshcoffe.store.application.dto.response.LocalDto;

import java.util.List;

public record SyncCatalogDataDto(
        List<CategoriaDto> categorias,
        List<ProductsWithFavoritesDto> productos,
        List<LocalDto> locales,
        List<DistritoDto> distritos,
        List<GrupoPersonalizacionDto> grupoPersonalizacion,
        List<ProductoGrupoDto> productoGrupoDto,
        List<OpcionPersonalizacionDto> opcionPersonalizacion
        ) {

}
