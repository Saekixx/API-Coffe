package com.api.covoshcoffe.common.infrastructure.web.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.covoshcoffe.common.domain.exeption.AccessDeniedException;
import com.api.covoshcoffe.common.domain.exeption.AlreadyExistsException;
import com.api.covoshcoffe.common.domain.exeption.BusinessException;
import com.api.covoshcoffe.common.domain.exeption.DomainException;
import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;
import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 404 - Recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseGlobal<Void>> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        log.warn("Recurso no encontrado en [{}]: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseGlobal.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    // 409 - Conflicto de entidad (Duplicados)
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ResponseGlobal<Void>> handleAlreadyExists(
            AlreadyExistsException ex, HttpServletRequest request) {
        log.warn("Conflicto de entidad en [{}]: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ResponseGlobal.error(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    // 422 - Regla de negocio inválida
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseGlobal<Void>> handleBusinessRule(BusinessException ex, HttpServletRequest request) {
        log.warn("Regla de negocio inválida en [{}]: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(ResponseGlobal.error(HttpStatus.UNPROCESSABLE_CONTENT.value(), ex.getMessage()));
    }

    // 400 - Validación de datos de entrada
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseGlobal<Void>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        log.warn("Error de validación en [{}]: {}", request.getRequestURI(), validationErrors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseGlobal.error(HttpStatus.BAD_REQUEST.value(), validationErrors));
    }

    // 401 - Credenciales inválidas
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseGlobal<Void>> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        log.warn("Credenciales inválidas en [{}]: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseGlobal.error(HttpStatus.UNAUTHORIZED.value(), "Credenciales incorrectas"));
    }

    // 403 - Acceso denegado
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseGlobal<Void>> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        log.warn("Acceso denegado en [{}]: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ResponseGlobal.error(HttpStatus.FORBIDDEN.value(), "No tienes permisos para realizar esta acción"));
    }

    // 400 - Excepción de dominio
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ResponseGlobal<Void>> handleDomainException(DomainException ex, HttpServletRequest request) {
        log.warn("Error de dominio en [{}]: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseGlobal.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    // 500 - Error interno del servidor
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseGlobal<Void>> handleGlobalException(Exception ex, HttpServletRequest request) {
        log.error("Error no controlado en [{}]: ", request.getRequestURI(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseGlobal.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrió un error interno en el servidor"));
    }
}
