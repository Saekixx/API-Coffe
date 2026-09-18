package com.api.covoshcoffe.order.domain.model;

import java.time.LocalDateTime;

enum MetodosPago {
    TARJETA_CREDITO,
    TARJETA_DEBITO,
    PAYPAL,
    TRANSFERENCIA_BANCARIA
}

enum EstadoPago {
    PENDIENTE,
    COMPLETADO,
    FALLIDO,
    REEMBOLSADO
}

public record Pagos(
        Integer id,
        Integer pedidoId,
        MetodosPago metodo,
        String proveedor,
        String ultimosDigitos,
        Double monto,
        EstadoPago estado,
        LocalDateTime pagadoEn
) {
    public Pagos(Integer pedidoId, MetodosPago metodo, String proveedor, String ultimosDigitos, Double monto, EstadoPago estado) {
        this(null, pedidoId, metodo, proveedor, ultimosDigitos, monto, estado, LocalDateTime.now());
    }
}
