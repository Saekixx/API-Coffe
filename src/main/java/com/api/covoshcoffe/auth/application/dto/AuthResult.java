package com.api.covoshcoffe.auth.application.dto;

public record AuthResult(
        String accessToken,
        String tokenType,
        Integer userId,
        String fullName,
        String email,
        String rol) {
}
