package com.api.covoshcoffe.auth.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.covoshcoffe.auth.application.dto.RegisterCommand;
import com.api.covoshcoffe.auth.application.ports.in.RegisterUseCase;
import com.api.covoshcoffe.auth.domain.model.Usuario;
import com.api.covoshcoffe.auth.domain.ports.out.PasswordEncoderPort;
import com.api.covoshcoffe.auth.domain.ports.out.UsuarioRepositoryPort;
import com.api.covoshcoffe.common.domain.exeption.AlreadyExistsException;

@Service
public class RegisterUseCaseImplement implements RegisterUseCase {
    private final PasswordEncoderPort passwordEncoder;
    private final UsuarioRepositoryPort usuarioRepository;

    public RegisterUseCaseImplement(PasswordEncoderPort passwordEncoder, UsuarioRepositoryPort usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Usuario register(RegisterCommand request) {
        // Validar que el correo no esté registrado
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new AlreadyExistsException("El email ya está registrado: " + request.email());
        }

        // Validar que las contraseñas coincidan
        if (!request.password().equals(request.passwordConfirmation())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        // Encriptar la contraseña
        String encodedPassword = passwordEncoder.encriptar(request.password());

        // Crear un nuevo usuario
        Usuario usuario = Usuario.crearNuevoLocal(request.fullname(), request.email(), encodedPassword);

        // Guardar el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }
}
