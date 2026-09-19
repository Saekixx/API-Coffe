package com.api.covoshcoffe.auth.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.auth.application.dto.UsuarioDto;

public interface GetUsersUseCase {
    List<UsuarioDto> getAllUsers();
}
