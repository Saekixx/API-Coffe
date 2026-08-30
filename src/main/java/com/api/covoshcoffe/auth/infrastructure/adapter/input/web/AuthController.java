package com.api.covoshcoffe.auth.infrastructure.adapter.input.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.covoshcoffe.auth.application.dto.AuthResult;
import com.api.covoshcoffe.auth.application.dto.LoginCommand;
import com.api.covoshcoffe.auth.application.dto.RegisterCommand;
import com.api.covoshcoffe.auth.application.ports.in.LoginUseCase;
import com.api.covoshcoffe.auth.application.ports.in.RegisterUseCase;
import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;

    public AuthController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseGlobal<AuthResult>> register(@RequestBody RegisterCommand entity) {
        registerUseCase.register(entity);
        AuthResult authResult = loginUseCase.login(entity.email(), entity.password());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseGlobal.success(authResult, "Usuario registrado exitosamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseGlobal<AuthResult>> login(@RequestBody LoginCommand entity) {
        AuthResult result = loginUseCase.login(entity.email(), entity.password());
        return ResponseEntity.ok(ResponseGlobal.success(result, "Usuario autenticado exitosamente"));
    }

}
