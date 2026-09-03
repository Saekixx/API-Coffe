package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String nombre;

    @Column
    String descripcion;

    @Column(name = "precio_base")
    Double precioBase;

    @Column(name = "imagen_url")
    String imagenUrl;

    @Column(name = "is_active")
    boolean isActive;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    CategoriaEntity categoria;

    @ManyToMany
    @JoinTable(name = "producto_grupos", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    Set<GrupoPersonalizacionEntity> grupos;
}
