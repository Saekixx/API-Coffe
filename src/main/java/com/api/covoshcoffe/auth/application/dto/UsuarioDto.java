package com.api.covoshcoffe.auth.application.dto;

public record UsuarioDto(
                Integer id,
                String nombres,
                String correo,
                String password,
                Integer login) {

}
