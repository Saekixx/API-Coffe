package com.api.covoshcoffe.store.infrastructure.adapter.output.persistence.entity;

import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "razon_social")
    String razonSocial;

    @Column
    String direccion;

    @Column(name = "horario")
    String horario;

    @Column
    Double latitud;

    @Column
    Double longitud;

    @Column(name = "is_active")
    boolean isActive;

    @ManyToOne
    @JoinColumn(name = "idDistrito")
    DistritoEntity distrito;
}
