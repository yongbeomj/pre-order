package com.shop.orderservice.order.controller;

import com.shop.orderservice.order.dto.response.OrderResponse;
import com.shop.orderservice.order.entity.Order;
import com.shop.orderservice.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class InternalOrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 단건 조회")
    @GetMapping("/orders")
    public ResponseEntity<OrderResponse> searchOrder(@RequestParam("orderId") Long orderId) {
        Order order = orderService.searchOrder(orderId);
        return ResponseEntity.ok(OrderResponse.of(order));
    }
}
