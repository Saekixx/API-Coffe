package com.api.covoshcoffe.auth.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.covoshcoffe.auth.application.dto.UsuarioDto;
import com.api.covoshcoffe.auth.application.ports.in.GetUsersUseCase;
import com.api.covoshcoffe.auth.domain.ports.out.UsuarioRepositoryPort;

@Service
public class GetUsersService implements GetUsersUseCase {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public GetUsersService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public List<UsuarioDto> getAllUsers() {
        return usuarioRepositoryPort.findAll().stream()
                .map(usuario -> new UsuarioDto(
                        usuario.id(),
                        usuario.fullname(),
                        usuario.email(),
                        usuario.password(),
                        0)) // No inicio sesion, esto se maneja desde el mismo app android
                .toList();
    }

}
