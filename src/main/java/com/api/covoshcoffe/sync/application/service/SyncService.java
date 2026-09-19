package com.api.covoshcoffe.sync.application.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.api.covoshcoffe.auth.application.services.GetUsersService;
import com.api.covoshcoffe.catalog.application.services.GetProductoService;
import com.api.covoshcoffe.order.application.services.GetOpcionPersonalizacionService;
import com.api.covoshcoffe.order.application.services.GetPedidoDetallePersonalizacionService;
import com.api.covoshcoffe.order.application.services.GetPedidoDetalleService;
import com.api.covoshcoffe.order.application.services.GetPedidoService;
import com.api.covoshcoffe.store.application.services.GetDistritoService;
import com.api.covoshcoffe.store.application.services.GetLocalService;

@Service
@Transactional(readOnly = true)
public class SyncService {
    private final GetUsersService getUsuariosService;
    private final GetProductoService getProductsService;
    private final GetLocalService getLocationsService;
    private final GetDistritoService getDistritosService;
    private final GetPedidoService getPedidosService;
    private final GetPedidoDetalleService getPedidoDetallesService;
    private final GetPedidoDetallePersonalizacionService getPedidoDetallePersonalizacionesService;
    private final GetOpcionPersonalizacionService getOpcionPersonalizacionesService;

    public SyncService(GetUsersService getUsuariosService, GetProductoService getProductsService,
            GetLocalService getLocationsService, GetDistritoService getDistritosService,
            GetPedidoService getPedidosService, GetPedidoDetalleService getPedidoDetallesService,
            GetPedidoDetallePersonalizacionService getPedidoDetallePersonalizacionesService,
            GetOpcionPersonalizacionService getOpcionPersonalizacionesService) {
        this.getUsuariosService = getUsuariosService;
        this.getProductsService = getProductsService;
        this.getLocationsService = getLocationsService;
        this.getDistritosService = getDistritosService;
        this.getPedidosService = getPedidosService;
        this.getPedidoDetallesService = getPedidoDetallesService;
        this.getPedidoDetallePersonalizacionesService = getPedidoDetallePersonalizacionesService;
        this.getOpcionPersonalizacionesService = getOpcionPersonalizacionesService;
    }
}
