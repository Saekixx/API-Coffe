package com.api.covoshcoffe.catalog.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column(name = "is_active")
    private boolean isActive;
}
