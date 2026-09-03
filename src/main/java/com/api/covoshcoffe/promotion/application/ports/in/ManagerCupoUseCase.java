package com.api.covoshcoffe.promotion.application.ports.in;

import java.util.List;

import com.api.covoshcoffe.promotion.application.dto.request.CreateCuponesRequest;
import com.api.covoshcoffe.promotion.application.dto.request.UpdateCuponesRequest;
import com.api.covoshcoffe.promotion.domain.model.Cupones;

public interface ManagerCupoUseCase {
    Cupones createCupo(CreateCuponesRequest request);

    Cupones updateCupo(Integer id, UpdateCuponesRequest request);

    List<Cupones> getAllCupos();

    List<Cupones> getAllActiveCupos();

    Cupones getCupoById(Integer id);

    String toggleStatus(Integer id);

    Cupones applyCupon(String code);

    String incrementUsageCount(Integer id);
}
