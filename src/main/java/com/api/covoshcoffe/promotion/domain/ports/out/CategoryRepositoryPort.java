package com.api.covoshcoffe.promotion.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.api.covoshcoffe.promotion.domain.model.Cupones;

public interface CategoryRepositoryPort {
    Cupones save(Cupones categoria);

    List<Cupones> findAll();

    List<Cupones> findAllActive();

    Optional<Cupones> findById(Integer id);

    Optional<Cupones> findByCode(String code);

}
