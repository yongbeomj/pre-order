package com.shop.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${feign.stock.name}", url = "${feign.stock.url}")
public interface StockClient {

    // 캐시 업데이트
}
