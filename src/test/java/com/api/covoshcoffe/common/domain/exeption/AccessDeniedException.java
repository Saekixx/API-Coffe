package com.api.covoshcoffe.common.domain.exeption;

// Exepciones de errores para cuando no se tiene acceso a un recurso
public class AccessDeniedException extends DomainException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
