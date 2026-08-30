package com.api.covoshcoffe.common.domain.exeption;

// Exepciones de errores para cuando ya existe un recurso (duplicados)
public class AlreadyExistsException extends DomainException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
