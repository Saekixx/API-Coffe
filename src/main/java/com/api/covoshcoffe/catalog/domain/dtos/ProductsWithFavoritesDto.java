package com.api.covoshcoffe.catalog.domain.dtos;

import java.math.BigDecimal;

public record ProductsWithFavoritesDto(
        Integer id,
        String detalle,
        String descripcion,
        BigDecimal precio,
        Integer categoria,
        Integer nuevo,
        Integer frecuente,
        Integer favorito
) {
}