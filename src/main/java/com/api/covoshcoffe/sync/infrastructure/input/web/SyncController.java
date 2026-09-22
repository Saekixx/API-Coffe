package com.api.covoshcoffe.sync.infrastructure.input.web;

import com.api.covoshcoffe.sync.application.dtos.SyncCatalogDataDto;
import com.api.covoshcoffe.sync.application.service.SyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/async")
public class SyncController {
    private final SyncService syncCatalogService;

    public SyncController(SyncService syncCatalogService) {
        this.syncCatalogService = syncCatalogService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<SyncCatalogDataDto> getSyncCatalogData(@PathVariable Integer userId) {
        return ResponseEntity.ok(syncCatalogService.getSyncCatalogData(userId));
    }
}
