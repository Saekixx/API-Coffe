package com.api.covoshcoffe.sync.application.dtos;

import com.api.covoshcoffe.auth.application.dto.UsuarioDto;
import com.api.covoshcoffe.catalog.application.dto.response.ProductoDto;
import com.api.covoshcoffe.order.application.dtos.response.OpcionPersonalizacionDto;
import com.api.covoshcoffe.order.application.dtos.response.PedidoDetalleDto;
import com.api.covoshcoffe.order.application.dtos.response.PedidoDetallePersonalizacionDto;
import com.api.covoshcoffe.order.application.dtos.response.PedidoDto;
import com.api.covoshcoffe.store.application.dto.response.DistritoDto;
import com.api.covoshcoffe.store.application.dto.response.LocalDto;

public record SyncDataDto(
        UsuarioDto[] usuario,
        ProductoDto[] producto,
        LocalDto[] local,
        DistritoDto[] distrito,
        PedidoDto[] pedido,
        PedidoDetalleDto[] pedidoDetalle,
        PedidoDetallePersonalizacionDto[] pedidoDetallePersonalizacion,
        OpcionPersonalizacionDto[] opcionPersonalizacion) {

}
