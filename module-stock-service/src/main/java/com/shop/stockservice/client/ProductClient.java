package com.shop.stockservice.client;

import com.shop.stockservice.dto.response.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "${feign.product.name}", url = "${feign.product.url}")
public interface ProductClient {

    // 재고 전체 조회 (Redis 캐시 데이터 동기화)
    @RequestMapping(method = RequestMethod.GET, value = "/products/stock-all", consumes = "application/json")
    List<StockResponse> searchAllStock();

}
