package com.api.covoshcoffe.store.infrastructure.adapter.input.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;
import com.api.covoshcoffe.store.application.services.GetStoreService;
import com.api.covoshcoffe.store.domain.model.Local;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {
    private final GetStoreService getStoreService;

    public StoreController(GetStoreService getStoreService) {
        this.getStoreService = getStoreService;
    }

    @GetMapping
    public ResponseEntity<ResponseGlobal<List<Local>>> getAllActiveLocales() {
        List<Local> response = getStoreService.getAllActiveStores();
        return ResponseEntity.ok(ResponseGlobal.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGlobal<Local>> getStoreById(@PathVariable Integer id) {
        Local response = getStoreService.getStoreById(id);
        return ResponseEntity.ok(ResponseGlobal.success(response));
    }

}
