package com.shop.orderservice.controller;

import com.shop.orderservice.entity.Order;
import com.shop.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class InternalOrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성")
    @PostMapping("/orders")
    public ResponseEntity<Long> createOrder(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        Order order = orderService.create(productId, quantity);

        return ResponseEntity.ok(order.getId());
    }
}
