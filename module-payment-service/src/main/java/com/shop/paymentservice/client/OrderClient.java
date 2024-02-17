package com.shop.paymentservice.client;

import com.shop.paymentservice.dto.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.order.name}", url = "${feign.order.url}")
public interface OrderClient {
    // 주문 조회
    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    ResponseEntity<OrderResponse> searchOrder(@RequestParam("orderId") Long orderId);
}
