package com.api.covoshcoffe.order.infrastructure.adapter.output.persistence.entity;

import com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity.OpcionPersonalizacionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_personalizaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePersonalizacionesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "detalle_pedido_id", nullable = false)
    DetallePedidosEntity detallePedido;

    @ManyToOne
    @JoinColumn(name = "opcion_id", nullable = false)
    OpcionPersonalizacionEntity opcion;
}
