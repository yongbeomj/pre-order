package com.shop.stockservice.stock.controller;

import com.shop.stockservice.stock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal/stocks")
@RequiredArgsConstructor
public class InternalStockController {

    private final StockService stockService;

    // Redis cache warming 용도
    @Operation(summary = "전체 재고 조회")
    @GetMapping()
    public ResponseEntity<?> searchAllStock() {
        stockService.searchAllStock();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "재고 생성")
    @PostMapping()
    public ResponseEntity<?> createStock(@RequestParam("productId") Long productId, @RequestParam("stock") Integer stock) {
        stockService.createStock(productId, stock);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "단품 재고 조회")
    @GetMapping("/{product_id}")
    public ResponseEntity<Integer> searchStock(@PathVariable("product_id") Long productId) {
        return ResponseEntity.ok(stockService.searchStock(productId));
    }

    @Operation(summary = "재고 증가")
    @PostMapping("/increase")
    public ResponseEntity<?> increaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        stockService.increaseStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "재고 감소")
    @PostMapping("/decrease")
    public ResponseEntity<?> decreaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        stockService.decreaseStock(productId, quantity);
        return ResponseEntity.ok().build();
    }

}
