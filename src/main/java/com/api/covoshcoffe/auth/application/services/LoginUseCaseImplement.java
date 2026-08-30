package com.api.covoshcoffe.auth.application.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.covoshcoffe.auth.application.dto.AuthResult;
import com.api.covoshcoffe.auth.application.ports.in.LoginUseCase;
import com.api.covoshcoffe.auth.domain.ports.out.PasswordEncoderPort;
import com.api.covoshcoffe.auth.domain.ports.out.TokenProviderPort;
import com.api.covoshcoffe.auth.domain.ports.out.UsuarioRepositoryPort;

@Service
public class LoginUseCaseImplement implements LoginUseCase {
    private final UsuarioRepositoryPort usuarioRepository;
    private final TokenProviderPort tokenProvider;
    private final PasswordEncoderPort passwordEncoder;

    public LoginUseCaseImplement(UsuarioRepositoryPort usuarioRepository, TokenProviderPort tokenProvider,
            PasswordEncoderPort passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public AuthResult login(String email, String password) {
        // Buscar el usuario por correo electrónico
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        // Verificar la contraseña
        if (!passwordEncoder.matches(password, usuario.password())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        // Generar un token de acceso (JWT)
        String accessToken = tokenProvider.generarToken(usuario);

        return new AuthResult(
                accessToken,
                "Bearer",
                usuario.id(),
                usuario.fullname(),
                usuario.email(),
                usuario.rol().name());
    }
}
