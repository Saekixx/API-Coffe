package com.api.covoshcoffe.auth.application.dto;

public record RegisterCommand(
        String fullname,
        String email,
        String password,
        String passwordConfirmation) {
}
