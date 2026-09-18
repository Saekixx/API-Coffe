package com.api.covoshcoffe.order.infrastructure.adapter.input.web;

import com.api.covoshcoffe.common.infrastructure.web.response.ResponseGlobal;
import com.api.covoshcoffe.order.application.ports.in.GetOrdersUseCase;
import com.api.covoshcoffe.order.domain.model.Pedidos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final GetOrdersUseCase getOrdersUseCase;

    public OrderController(GetOrdersUseCase getOrdersUseCase) {
        this.getOrdersUseCase = getOrdersUseCase;
    }

    @GetMapping
    public ResponseEntity<ResponseGlobal<List<Pedidos>>> getAllOrders() {
        return ResponseEntity.ok(ResponseGlobal.success(getOrdersUseCase.getAllOrders()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGlobal<Pedidos>> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(ResponseGlobal.success(getOrdersUseCase.getOrderById(id)));
    }

    @GetMapping("/user/{usuarioId}")
    public ResponseEntity<ResponseGlobal<List<Pedidos>>> getOrdersByUsuarioId(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(ResponseGlobal.success(getOrdersUseCase.getOrdersByUsuarioId(usuarioId)));
    }

    @GetMapping("/local/{localId}")
    public ResponseEntity<ResponseGlobal<List<Pedidos>>> getOrdersByLocalId(@PathVariable Integer localId) {
        return ResponseEntity.ok(ResponseGlobal.success(getOrdersUseCase.getOrdersByLocalId(localId)));
    }

    @GetMapping("/local/{localId}/status")
    public ResponseEntity<ResponseGlobal<List<Pedidos>>> getOrdersByLocalIdAndEstado(
            @PathVariable Integer localId,
            @RequestParam String estado) {
        return ResponseEntity.ok(ResponseGlobal.success(getOrdersUseCase.getOrdersByLocalIdAndEstado(localId, estado)));
    }
}