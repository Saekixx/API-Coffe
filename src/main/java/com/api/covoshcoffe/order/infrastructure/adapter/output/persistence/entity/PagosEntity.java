package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagosEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    PedidosEntity pedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo", nullable = false)
    MetodosPago metodo;

    @Column
    String proveedor;

    @Column(name = "ultimos_4_digitos")
    String ultimosDigitos;

    @Column
    Double monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
    EstadoPago estado;

    @Column(name = "pagado_en")
    LocalDateTime pagadoEn;

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
