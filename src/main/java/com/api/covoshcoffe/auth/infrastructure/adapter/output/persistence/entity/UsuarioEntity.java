package com.api.covoshcoffe.auth.infrastructure.adapter.output.persistence.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.api.covoshcoffe.auth.domain.model.AuthProveedor;
import com.api.covoshcoffe.auth.domain.model.Rol;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_completo", nullable = false)
    private String fullname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "proveedor_auth")
    private AuthProveedor proveedorAuth;

    @Column(name = "proveedor_id")
    private String proveedorId;

    @Column
    private Integer puntos;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false, name = "updated_at")
    private LocalDateTime updatedAt;
}
