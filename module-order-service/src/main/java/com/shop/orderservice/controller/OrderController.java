package com.shop.orderservice.controller;

import com.shop.orderservice.common.response.ResponseDto;
import com.shop.orderservice.dto.request.OrderCreateRequest;
import com.shop.orderservice.dto.response.OrderResponse;
import com.shop.orderservice.entity.Order;
import com.shop.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
