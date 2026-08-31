package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity;

import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String nombre;

    @Column
    String direccion;

    @Column
    String ciudad;

    @Column
    Double latitud;

    @Column
    Double longitud;

    @Column(name = "horario_apertura")
    LocalTime horarioApertura;

    @Column(name = "horario_cierre")
    LocalTime horarioCierre;

    @Column(name = "is_active")
    boolean isActive;
}
