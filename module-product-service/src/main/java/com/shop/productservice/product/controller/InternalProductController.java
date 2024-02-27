package com.shop.productservice.product.controller;

import com.shop.productservice.product.dto.response.ProductResponse;
import com.shop.productservice.product.dto.response.ProductStockResponse;
import com.shop.productservice.product.entity.Product;
import com.shop.productservice.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internal/products")
@RequiredArgsConstructor
public class InternalProductController {

    private final ProductService productService;

    @Operation(summary = "상품 정보 조회")
    @GetMapping()
    public ResponseEntity<ProductResponse> searchProduct(@RequestParam("productId") Long productId) {
        Product product = productService.searchProductInfo(productId);
        return ResponseEntity.ok(ProductResponse.of(product));
    }

    @Operation(summary = "전체 상품 재고 조회")
    @GetMapping("/stock-all")
    public ResponseEntity<List<ProductStockResponse>> searchAllStock() {
        List<ProductStockResponse> stockResponses = productService.searchAllStock()
                .stream().map(ProductStockResponse::of)
                .toList();

        return ResponseEntity.ok(stockResponses);
    }

    @Operation(summary = "단품 재고 조회")
    @GetMapping("/stock")
    public ResponseEntity<Integer> searchStock(@RequestParam("productId") Long productId) {
        Product product = productService.searchProductInfo(productId);
        return ResponseEntity.ok(product.getStock());
    }

    @Operation(summary = "재고 업데이트")
    @PostMapping("/stock/update")
    public ResponseEntity<?> updateStock(@RequestParam("productId") Long productId, @RequestParam("stock") Integer stock) {
        productService.updateStock(productId, stock);
        return ResponseEntity.ok().build();
    }

}
