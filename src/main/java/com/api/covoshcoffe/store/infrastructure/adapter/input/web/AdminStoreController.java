package com.api.covoshcoffe.store.infrastructure.adapter.input.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;
import com.api.covoshcoffe.store.application.dto.request.CreateLocalCommand;
import com.api.covoshcoffe.store.application.dto.request.UpdateLocalCommand;
import com.api.covoshcoffe.store.application.ports.ManagerStoreUseCase;
import com.api.covoshcoffe.store.domain.model.Local;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/admin/stores")
@PreAuthorize("hasRole('ADMIN')")
public class AdminStoreController {
    private final ManagerStoreUseCase manageStoreService;

    public AdminStoreController(ManagerStoreUseCase manageStoreService) {
        this.manageStoreService = manageStoreService;
    }

    @GetMapping
    public ResponseEntity<ResponseGlobal<List<Local>>> getAllLocales() {
        List<Local> response = manageStoreService.getAllStores();
        return ResponseEntity.ok(ResponseGlobal.success(response));
    }

    @PostMapping
    public ResponseEntity<ResponseGlobal<Local>> createStore(@RequestBody CreateLocalCommand command) {
        Local response = manageStoreService.createStore(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseGlobal.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseGlobal<Local>> updateStore(@PathVariable Integer id,
            @RequestBody UpdateLocalCommand command) {
        Local response = manageStoreService.updateStore(id, command);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseGlobal.success(response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseGlobal<String>> toggleStoreStatus(
            @PathVariable Integer id) {
        String message = manageStoreService.toggleStoreStatus(id);
        return ResponseEntity.ok(ResponseGlobal.success(message));
    }
}
