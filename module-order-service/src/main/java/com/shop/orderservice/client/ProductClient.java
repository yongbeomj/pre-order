package com.shop.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.product.name}", url = "${feign.product.url}")
public interface ProductClient {
    // 재고 확인
    @RequestMapping(method = RequestMethod.GET, value = "/products/stock")
    ResponseEntity<Integer> searchStock(@RequestParam("productId") Long productId);

    // 재고 증가
    @RequestMapping(method = RequestMethod.POST, value = "/products/stock/increase")
    void increaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity);

    // 재고 차감
    @RequestMapping(method = RequestMethod.POST, value = "/products/stock/decrease")
    void decreaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity);

}
