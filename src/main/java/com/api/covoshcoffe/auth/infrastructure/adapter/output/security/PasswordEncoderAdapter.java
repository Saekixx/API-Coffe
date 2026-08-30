package com.api.covoshcoffe.auth.infrastructure.adapter.output.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.covoshcoffe.auth.domain.ports.out.PasswordEncoderPort;

@Component
public class PasswordEncoderAdapter implements PasswordEncoderPort {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encriptar(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
