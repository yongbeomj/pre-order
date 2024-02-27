package com.shop.stockservice.client;

import com.shop.stockservice.dto.response.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${feign.product.name}", url = "${feign.product.url}")
public interface ProductClient {

    // 상품 재고 전체 조회 (Redis 캐시 데이터 동기화)
    @RequestMapping(method = RequestMethod.GET, value = "/products/stock-all", consumes = "application/json")
    List<StockResponse> searchAllStock();

    // 상품 재고 조회
    @RequestMapping(method = RequestMethod.GET, value = "/products/stock", consumes = "application/json")
    Integer searchStock(@RequestParam("productId") Long productId);

    // 재고 업데이트
    @RequestMapping(method = RequestMethod.POST, value = "/products/stock/update", consumes = "application/json")
    Integer updateStock(@RequestParam("productId") Long productId, @RequestParam("stock") Integer stock);



}
