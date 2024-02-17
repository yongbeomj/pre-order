package com.shop.productservice.controller;

import com.shop.productservice.entity.Product;
import com.shop.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/products")
@RequiredArgsConstructor
public class InternalProductController {

    private final ProductService productService;

    @Operation(summary = "재고 조회")
    @GetMapping("/stock")
    public ResponseEntity<Integer> searchStock(@RequestParam("productId") Long productId) {
        Product product = productService.searchProductInfo(productId);
        return ResponseEntity.ok(product.getStock());
    }

    @Operation(summary = "재고 증가")
    @PostMapping("/stock/increase")
    public ResponseEntity<?> increaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        productService.increaseStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "재고 감소")
    @PostMapping("/stock/decrease")
    public ResponseEntity<?> decreaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        productService.decreaseStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

}