package com.api.covoshcoffe.promotion.infrastructure.input.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;
import com.api.covoshcoffe.promotion.application.dto.request.CreateCuponesRequest;
import com.api.covoshcoffe.promotion.application.dto.request.UpdateCuponesRequest;
import com.api.covoshcoffe.promotion.application.ports.in.ManagerCupoUseCase;
import com.api.covoshcoffe.promotion.domain.model.Cupones;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/admin/cupones")
@PreAuthorize("hasRole('ADMIN')")
public class ManagerCuponController {
    private final ManagerCupoUseCase managerCuponUseCase;

    public ManagerCuponController(ManagerCupoUseCase managerCuponUseCase) {
        this.managerCuponUseCase = managerCuponUseCase;
    }

    @PostMapping
    public ResponseEntity<ResponseGlobal<Cupones>> postMethodName(@RequestBody CreateCuponesRequest request) {
        Cupones cupon = managerCuponUseCase.createCupo(request);
        return ResponseEntity.ok(ResponseGlobal.success(cupon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGlobal<Cupones>> putMethodName(@PathVariable String id,
            @RequestBody UpdateCuponesRequest request) {
        Cupones cupon = managerCuponUseCase.updateCupo(Integer.valueOf(id), request);
        return ResponseEntity.ok(ResponseGlobal.success(cupon));
    }

    @GetMapping("")
    public ResponseEntity<ResponseGlobal<List<Cupones>>> getMethodName() {
        List<Cupones> cupones = managerCuponUseCase.getAllCupos();
        return ResponseEntity.ok(ResponseGlobal.success(cupones));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseGlobal<List<Cupones>>> getActiveCupones() {
        List<Cupones> cupones = managerCuponUseCase.getAllActiveCupos();
        return ResponseEntity.ok(ResponseGlobal.success(cupones));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGlobal<Cupones>> getCupoById(@PathVariable String id) {
        Cupones cupon = managerCuponUseCase.getCupoById(Integer.valueOf(id));
        return ResponseEntity.ok(ResponseGlobal.success(cupon));
    }

    @GetMapping("/apply")
    public ResponseEntity<ResponseGlobal<Cupones>> getMethodName(@RequestParam String code) {
        Cupones cupon = managerCuponUseCase.applyCupon(code);
        return ResponseEntity.ok(ResponseGlobal.success(cupon));
    }

    @PostMapping("/use/{id}")
    public ResponseEntity<ResponseGlobal<String>> postMethodName(@PathVariable String id) {
        String result = managerCuponUseCase.incrementUsageCount(Integer.valueOf(id));
        return ResponseEntity.ok(ResponseGlobal.success(result));
    }

    @PutMapping("/toggle/{id}")
    public ResponseEntity<ResponseGlobal<String>> toggleStatus(@PathVariable String id) {
        String result = managerCuponUseCase.toggleStatus(Integer.valueOf(id));
        return ResponseEntity.ok(ResponseGlobal.success(result));
    }

}
