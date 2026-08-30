package com.api.covoshcoffe.common.domain.exeption;

// Exepciones de errores de negocio
public class BusinessException extends DomainException {
    public BusinessException(String message) {
        super(message);
    }
}
