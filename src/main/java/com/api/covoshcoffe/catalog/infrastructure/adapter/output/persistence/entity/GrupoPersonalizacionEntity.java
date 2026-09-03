package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grupos_personalizacion")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GrupoPersonalizacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column(name = "es_obligatorio")
    private Boolean esObligatorio;

    @Column(name = "max_seleccion")
    private Integer maxSeleccion;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "grupoPersonalizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OpcionPersonalizacionEntity> opciones;

    @ManyToMany(mappedBy = "grupos")
    private Set<ProductoEntity> productos;
}
