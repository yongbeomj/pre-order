package com.shop.orderservice.client;

import com.shop.orderservice.order.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.product.name}", url = "${feign.product.url}")
public interface ProductClient {

    // 상품 조회
    @RequestMapping(method = RequestMethod.GET, value = "/products", consumes = "application/json")
    ProductResponse searchProduct(@RequestParam("productId") Long productId);
}
