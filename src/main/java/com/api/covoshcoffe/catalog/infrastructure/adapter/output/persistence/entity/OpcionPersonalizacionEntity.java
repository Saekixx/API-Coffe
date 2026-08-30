package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "opciones_personalizacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpcionPersonalizacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String nombre;

    @Column(name = "precio_adicional")
    Double precioAdicional;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    GrupoPersonalizacionEntity grupoPersonalizacion;
}
