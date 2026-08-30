package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String nombre;

    @Column(name = "volumen_ml")
    Integer volumenMl;

    @Column(name = "precio_adicional")
    Double precioAdicional;

    @Column(name = "is_active")
    boolean isActive;
}
