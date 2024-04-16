package com.shop.stockservice.stock.controller;

import com.shop.stockservice.common.response.ResponseDto;
import com.shop.stockservice.stock.dto.response.StockResponse;
import com.shop.stockservice.stock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    // Redis cache warming 용도
    @Operation(summary = "전체 재고 조회")
    @GetMapping()
    public ResponseEntity<?> searchAllStock() {
        stockService.searchAllStock();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "단품 재고 조회")
    @GetMapping("/{product_id}")
    public ResponseDto<StockResponse> searchStock(@PathVariable("product_id") Long productId) {
        Integer stock = stockService.searchStock(productId);

        return ResponseDto.success(StockResponse.of(productId, stock));
    }

    @Operation(summary = "재고 증가")
    @PostMapping("/increase")
    public ResponseEntity<?> increaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        stockService.updateStock(productId, quantity, "incr");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "재고 감소")
    @PostMapping("/decrease")
    public ResponseEntity<?> decreaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
        stockService.updateStock(productId, quantity, "decr");
        return ResponseEntity.ok().build();
    }

}
