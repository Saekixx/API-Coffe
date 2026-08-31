package com.api.covoshcoffe.catalog.application.dto.request;

public record UpdateCategoryCommand(
        Integer id,
        String nombre,
        boolean isActive) {

}
