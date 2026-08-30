package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
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
}
