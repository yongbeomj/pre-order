package com.shop.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.stock.name}", url = "${feign.stock.url}")
public interface StockClient {
    // 재고 생성
    @RequestMapping(method = RequestMethod.POST, value = "/stocks")
    void createStock(@RequestParam("productId") Long productId, @RequestParam("stock") Integer stock);
}
