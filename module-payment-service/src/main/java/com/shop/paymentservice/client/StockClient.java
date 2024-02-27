package com.shop.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.stock.name}", url = "${feign.stock.url}")
public interface StockClient {
    // 재고 조회
    @RequestMapping(method = RequestMethod.GET, value = "/stocks")
    Integer searchStock(@RequestParam("productId") Long productId);

    // 재고 증가
    @RequestMapping(method = RequestMethod.POST, value = "/stocks/increase")
    void increaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity);

    // 재고 차감
    @RequestMapping(method = RequestMethod.POST, value = "/stocks/decrease")
    void decreaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity);
}
