package com.api.covoshcoffe.promotion.application.services;

import java.util.List;

import com.api.covoshcoffe.common.domain.exeption.BusinessException;
import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;
import com.api.covoshcoffe.promotion.application.dto.request.CreateCuponesRequest;
import com.api.covoshcoffe.promotion.application.dto.request.UpdateCuponesRequest;
import com.api.covoshcoffe.promotion.application.ports.in.ManagerCupoUseCase;
import com.api.covoshcoffe.promotion.domain.model.Cupones;
import com.api.covoshcoffe.promotion.domain.ports.out.CategoryRepositoryPort;

public class ManageCuponService implements ManagerCupoUseCase {
    private final CategoryRepositoryPort categoryRepositoryPort;

    public ManageCuponService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public List<Cupones> getAllCupos() {
        return categoryRepositoryPort.findAll();
    }

    @Override
    public List<Cupones> getAllActiveCupos() {
        return categoryRepositoryPort.findAllActive();
    }

    @Override
    public Cupones getCupoById(Integer id) {
        return categoryRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado:" + id));
    }

    @Override
    public Cupones createCupo(CreateCuponesRequest request) {
        Cupones cupon = new Cupones(
                request.codigo(),
                request.descuento(),
                request.limiteUsos(),
                request.usosActuales(),
                request.fechaExpiracion(),
                true);

        return categoryRepositoryPort.save(cupon);
    }

    @Override
    public Cupones updateCupo(Integer id, UpdateCuponesRequest request) {
        Cupones cupon = categoryRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado:" + id));

        Cupones updatedCupon = new Cupones(
                cupon.id(),
                request.codigo(),
                request.descuento(),
                request.limiteUsos(),
                request.usosActuales(),
                request.fechaExpiracion(),
                cupon.activo());

        return categoryRepositoryPort.save(updatedCupon);
    }

    @Override
    public Cupones applyCupon(String code) {
        Cupones cupon = categoryRepositoryPort.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado:" + code));

        if (!cupon.activo())
            throw new BusinessException("Cupon no disponible, esta inactivo");

        if (cupon.limiteUsos() != null && cupon.usosActuales() >= cupon.limiteUsos())
            throw new BusinessException("El cupón alcanzó su límite de usos antes de procesar el incremento.");

        return cupon;
    }

    @Override
    public String incrementUsageCount(Integer id) {
        Cupones cupon = categoryRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado:" + id));

        if (!cupon.activo())
            throw new BusinessException("Cupon no disponible, esta inactivo");

        if (cupon.limiteUsos() != null && cupon.usosActuales() >= cupon.limiteUsos())
            throw new BusinessException("El cupón alcanzó su límite de usos antes de procesar el incremento.");

        Cupones updatedCupon = new Cupones(
                cupon.id(),
                cupon.codigo(),
                cupon.descuento(),
                cupon.limiteUsos(),
                cupon.usosActuales() + 1,
                cupon.fechaExpiracion(),
                cupon.activo());

        categoryRepositoryPort.save(updatedCupon);
        return "Usos actualizados";
    }

    @Override
    public String toggleStatus(Integer id) {
        Cupones cupon = categoryRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado:" + id));

        Cupones updatedCupon = new Cupones(
                cupon.id(),
                cupon.codigo(),
                cupon.descuento(),
                cupon.limiteUsos(),
                cupon.usosActuales(),
                cupon.fechaExpiracion(),
                !cupon.activo());

        categoryRepositoryPort.save(updatedCupon);

        return cupon.activo() ? "Cupon desactivado" : "Cupon activado";
    }
}
