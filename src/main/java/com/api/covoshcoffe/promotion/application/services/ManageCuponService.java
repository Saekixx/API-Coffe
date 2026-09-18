package com.api.covoshcoffe.promotion.application.services;

import java.util.List;

import com.api.covoshcoffe.promotion.domain.ports.out.CuponesRepositoryPort;
import org.springframework.stereotype.Service;

import com.api.covoshcoffe.common.domain.exeption.BusinessException;
import com.api.covoshcoffe.common.domain.exeption.ResourceNotFoundException;
import com.api.covoshcoffe.promotion.application.dto.request.CreateCuponesRequest;
import com.api.covoshcoffe.promotion.application.dto.request.UpdateCuponesRequest;
import com.api.covoshcoffe.promotion.application.ports.in.ManagerCuponUseCase;
import com.api.covoshcoffe.promotion.domain.model.Cupones;

@Service
public class ManageCuponService implements ManagerCuponUseCase {
    private final CuponesRepositoryPort cuponesRepositoryPort;


    public ManageCuponService(CuponesRepositoryPort cuponesRepositoryPort) {
        this.cuponesRepositoryPort = cuponesRepositoryPort;
    }

    @Override
    public List<Cupones> getAllCupos() {
        return cuponesRepositoryPort.findAll();
    }

    @Override
    public List<Cupones> getAllActiveCupos() {
        return cuponesRepositoryPort.findAllActive();
    }

    @Override
    public Cupones getCupoById(Integer id) {
        return cuponesRepositoryPort.findById(id)
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

        return cuponesRepositoryPort.save(cupon);
    }

    @Override
    public Cupones updateCupo(Integer id, UpdateCuponesRequest request) {
        Cupones cupon = cuponesRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado:" + id));

        Cupones updatedCupon = new Cupones(
                cupon.id(),
                request.codigo(),
                request.descuento(),
                request.limiteUsos(),
                request.usosActuales(),
                request.fechaExpiracion(),
                cupon.activo());

        return cuponesRepositoryPort.save(updatedCupon);
    }

    @Override
    public Cupones applyCupon(String code) {
        Cupones cupon = cuponesRepositoryPort.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado:" + code));

        if (!cupon.activo())
            throw new BusinessException("Cupon no disponible, esta inactivo");

        if (cupon.limiteUsos() != null && cupon.usosActuales() >= cupon.limiteUsos())
            throw new BusinessException("El cupón alcanzó su límite de usos antes de procesar el incremento.");

        return cupon;
    }

    @Override
    public String incrementUsageCount(Integer id) {
        Cupones cupon = cuponesRepositoryPort.findById(id)
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

        cuponesRepositoryPort.save(updatedCupon);
        return "Usos actualizados";
    }

    @Override
    public String toggleStatus(Integer id) {
        Cupones cupon = cuponesRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cupon no encontrado:" + id));

        Cupones updatedCupon = new Cupones(
                cupon.id(),
                cupon.codigo(),
                cupon.descuento(),
                cupon.limiteUsos(),
                cupon.usosActuales(),
                cupon.fechaExpiracion(),
                !cupon.activo());

        cuponesRepositoryPort.save(updatedCupon);

        return cupon.activo() ? "Cupon desactivado" : "Cupon activado";
    }
}
