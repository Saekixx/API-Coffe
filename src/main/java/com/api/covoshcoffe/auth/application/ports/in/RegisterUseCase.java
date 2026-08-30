package com.api.covoshcoffe.auth.application.ports.in;

import com.api.covoshcoffe.auth.application.dto.RegisterCommand;
import com.api.covoshcoffe.auth.domain.model.Usuario;

public interface RegisterUseCase {
    Usuario register(RegisterCommand command);

}
