package com.shop.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.order.name}", url = "${feign.order.url}")
public interface OrderClient {
    // 주문 생성
    @RequestMapping(method = RequestMethod.POST, value = "/orders")
    ResponseEntity<Long> createOrder(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity);

}
