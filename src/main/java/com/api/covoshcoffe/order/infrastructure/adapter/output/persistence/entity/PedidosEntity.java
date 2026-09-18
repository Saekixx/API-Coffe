package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity;

import com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.entity.UsuarioEntity;
import com.api.covoshcoffe.promotion.infrastructure.output.persistence.entity.CuponesEntity;
import com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity.LocalEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id", nullable = false)
    LocalEntity local;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cupon_id")
    CuponesEntity cupones;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_entrega", nullable = false)
    MetodoEntrega metodoEntrega;

    @Column(name = "fecha_entrega")
    LocalDateTime fechaEntrega;

    @Column(name = "subtotal")
    Double subTotal;

    @Column(name = "descuento")
    Double descuento;

    @Column(name = "total")
    Double total;

    @Column(name = "items_total")
    Integer itemsTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    EstadoPedido estado;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;


    public enum MetodoEntrega{
        EN_LOCAL,
        DELIVERY
    }

    public enum EstadoPedido{
        PENDIENTE,
        EN_PREPARACION,
        LISTO,
        EN_CAMINO,
        ENTREGADO,
        CANCELADO
    }
}
