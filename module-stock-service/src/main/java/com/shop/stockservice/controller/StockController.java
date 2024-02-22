package com.shop.stockservice.controller;

import com.shop.stockservice.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    // Redis 캐시 데이터 동기화
    @Operation(summary = "전체 재고 조회")
    @GetMapping()
    public ResponseEntity<?> searchStockAll() {
        stockService.searchStockAll();
        return ResponseEntity.ok("success");
    }

}
