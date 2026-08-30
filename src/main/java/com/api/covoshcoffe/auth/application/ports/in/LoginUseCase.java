package com.api.covoshcoffe.auth.application.ports.in;

import com.api.covoshcoffe.auth.application.dto.AuthResult;

public interface LoginUseCase {
        AuthResult login(String email, String password);
}
