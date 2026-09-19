package com.api.covoshcoffe.store.domain.model;

public record Distrito(
        Integer id,
        String detalle) {
    public Distrito(String detalle) {
        this(null, detalle);
    }
}
