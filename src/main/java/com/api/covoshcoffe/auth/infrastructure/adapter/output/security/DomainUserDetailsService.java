package com.api.covoshcoffe.auth.infrastructure.adapter.output.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.covoshcoffe.auth.domain.model.Usuario;
import com.api.covoshcoffe.auth.domain.ports.out.UsuarioRepositoryPort;

@Service
public class DomainUserDetailsService implements UserDetailsService {
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public DomainUserDetailsService(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario = usuarioRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        return new User(
                usuario.email(),
                usuario.password() != null ? usuario.password() : "",
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.rol().name())));
    }
}
