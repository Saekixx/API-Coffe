package com.api.covoshcoffe.promotion.infrastructure.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cupones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuponesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String codigo;

    @Column
    Double descuento;

    @Column(name = "limite_usos")
    Integer limiteUsos;

    @Column(name = "usos_actuales")
    Integer usosActuales;

    @Column(name = "fecha_expiracion")
    String fechaExpiracion;

    @Column
    boolean activo;
}
