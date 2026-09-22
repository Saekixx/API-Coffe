package com.api.covoshcoffe.sync.application.service;

import com.api.covoshcoffe.sync.application.dtos.SyncCatalogDataDto;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.api.covoshcoffe.catalog.application.services.GetProductoService;
import com.api.covoshcoffe.store.application.services.GetDistritoService;
import com.api.covoshcoffe.store.application.services.GetLocalService;

@Service
@Transactional(readOnly = true)
public class SyncService {
    private final GetProductoService getProductsService;
    private final GetLocalService getLocationsService;
    private final GetDistritoService getDistritosService;

    public SyncService(GetProductoService getProductsService,
                       GetLocalService getLocationsService, GetDistritoService getDistritosService) {
        this.getProductsService = getProductsService;
        this.getLocationsService = getLocationsService;
        this.getDistritosService = getDistritosService;
    }

    public SyncCatalogDataDto getSyncCatalogData(Integer usuarioId) {
        var categories = getProductsService.getAllCategorias();
        var products = getProductsService.findAllWithFavorites(usuarioId);
        var locations = getLocationsService.getAllLocales();
        var distritos = getDistritosService.getAllDistritos();
        var grupoPersonalizacion = getProductsService.getGrupoPersonalizacion();
        var productoGrupo = getProductsService.findAllWithGruposAndOpciones();
        var opcionPersonalizacion = getProductsService.getOpcionPersonalizacion();

        return new SyncCatalogDataDto(categories, products, locations, distritos, grupoPersonalizacion, productoGrupo, opcionPersonalizacion);
    }
}
