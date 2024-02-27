package com.foodlabs.controller;

import com.foodlabs.dto.request.OrderRequest;
import com.foodlabs.dto.response.OrderResponse;
import com.foodlabs.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("{orderId}")
    public OrderResponse getOrder(@PathVariable final UUID orderId) {
        return service.getOrderById(orderId);
    }

    @GetMapping("user/{userId}")
    public List<OrderResponse> getUserOrders(@PathVariable final UUID userId) {
        return service.getAllByUserId(userId);
    }

    @PostMapping("user/{userId}")
    public OrderResponse createOrder(@PathVariable final UUID userId,
                                     @RequestBody @Valid final OrderRequest request) {
        return service.createOrder(userId, request);
    }

}
