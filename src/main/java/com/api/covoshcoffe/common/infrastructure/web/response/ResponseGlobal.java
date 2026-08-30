package com.api.covoshcoffe.common.infrastructure.web.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseGlobal<T>(
    boolean success,
    int status,
    String message,
    T data,
    Instant timestamp
) {
    public static <T> ResponseGlobal<T> success(int status, T data, String message) {
        return new ResponseGlobal<>(true, status, message, data, Instant.now());
    }

    public static <T> ResponseGlobal<T> success(T data, String message) {
        return new ResponseGlobal<>(true, 200, message, data, Instant.now());
    }

    public static <T> ResponseGlobal<T> success(T data) {
        return new ResponseGlobal<>(true, 200, "Operación exitosa", data, Instant.now());
    }

    public static <T> ResponseGlobal<T> error(int status, String message) {
        return new ResponseGlobal<>(false, status, message, null, Instant.now());
    }

}
