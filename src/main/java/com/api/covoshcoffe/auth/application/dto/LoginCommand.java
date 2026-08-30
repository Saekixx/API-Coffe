package com.api.covoshcoffe.auth.application.dto;

public record LoginCommand(
        String email,
        String password) {
}
