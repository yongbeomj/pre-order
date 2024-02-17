package com.shop.orderservice.controller;

import com.shop.orderservice.common.response.ResponseDto;
import com.shop.orderservice.dto.request.OrderCreateRequest;
import com.shop.orderservice.dto.response.OrderResponse;
import com.shop.orderservice.entity.Order;
import com.shop.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 등록")
    @PostMapping()
    public ResponseDto<OrderResponse> createOrder(@RequestBody OrderCreateRequest request) {
        Order order = orderService.createOrder(request);
        return ResponseDto.success(OrderResponse.of(order));
    }

    @Operation(summary = "주문 단건 조회")
    @GetMapping("/{order_id}")
    public ResponseDto<OrderResponse> searchOrder(@PathVariable("order_id") Long orderId) {
        Order order = orderService.searchOrder(orderId);
        return ResponseDto.success(OrderResponse.of(order));
    }

}
