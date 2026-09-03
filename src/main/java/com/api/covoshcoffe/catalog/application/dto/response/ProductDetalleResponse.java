package com.api.covoshcoffe.catalog.application.dto.response;

import java.util.List;

import com.api.covoshcoffe.catalog.domain.model.Producto;

public record ProductDetalleResponse(
        Integer id,
        String nombre,
        String descripcion,
        Double precioBase,
        String imagenUrl,
        Boolean isActive,
        CategoryResponse categoria,
        List<GrupoPersonalizacionResponse> grupos) {
    public static ProductDetalleResponse fromDomain(Producto producto) {
        if (producto == null)
            return null;

        CategoryResponse catResp = producto.categoria() != null
                ? new CategoryResponse(
                        producto.categoria().id(),
                        producto.categoria().nombre(),
                        producto.categoria().isActive())
                : null;

        List<GrupoPersonalizacionResponse> gruposResp = producto.grupos() != null
                ? producto.grupos().stream()
                        .map(GrupoPersonalizacionResponse::fromDomain)
                        .toList()
                : List.of();

        return new ProductDetalleResponse(
                producto.id(),
                producto.nombre(),
                producto.descripcion(),
                producto.precioBase(),
                producto.imagenUrl(),
                producto.isActive(),
                catResp,
                gruposResp);
    }
}
