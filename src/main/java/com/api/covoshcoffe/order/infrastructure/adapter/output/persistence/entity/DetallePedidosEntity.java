package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.MedidaEntity;
import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.ProductoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    PedidosEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    ProductoEntity producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medida_id", nullable = false)
    MedidaEntity medida;

    @Column
    Integer cantidad;

    @Column(name = "precio_unitario")
    Double precioUnitario;

    @Column(name = "subtotal", insertable = false, updatable = false)
    Double subTotal;
}
