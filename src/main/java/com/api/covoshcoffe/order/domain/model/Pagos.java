package com.api.covoshcoffe.order.domain.model;

import java.time.LocalDateTime;

public record Pagos(
        Integer id,
        Pedidos pedido,
        MetodosPago metodo,
        String proveedor,
        String ultimosDigitos,
        Double monto,
        EstadoPago estado,
        LocalDateTime pagadoEn
) {
    public Pagos(Pedidos pedido, MetodosPago metodo, String proveedor, String ultimosDigitos, Double monto, EstadoPago estado) {
        this(null, pedido, metodo, proveedor, ultimosDigitos, monto, estado, LocalDateTime.now());
    }

    public enum MetodosPago {
        TARJETA_CREDITO,
        TARJETA_DEBITO,
        PAYPAL,
        TRANSFERENCIA_BANCARIA
    }

    public enum EstadoPago {
        PENDIENTE,
        COMPLETADO,
        FALLIDO,
        REEMBOLSADO
    }
}
