package com.shop.stockservice.service;

import com.shop.stockservice.client.ProductClient;
import com.shop.stockservice.common.exception.BaseException;
import com.shop.stockservice.common.response.ErrorCode;
import com.shop.stockservice.dto.response.StockResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductClient productClient;
    private final StockCacheService stockCacheService;

    // 재고 생성 (상품 등록)
    public void createStock(Long productId, Integer stock) {
        stockCacheService.setData(productId.toString(), stock.toString());
    }

    // 전체 재고 조회 (redis warming)
    public void searchAllStock() {
        List<StockResponse> stockResponses = productClient.searchAllStock();

        for (StockResponse response : stockResponses) {
            stockCacheService.setData(response.getProductId().toString(), response.getStock().toString());
        }
    }

    // 단품 재고 조회
    public synchronized Integer searchStock(Long productId) {
        // redis 조회
        String cacheStock = stockCacheService.getData(productId.toString());
        if (cacheStock != null) {
            return Integer.parseInt(cacheStock);
        }

        // DB 조회 (캐시 없을 경우)
        Integer dbStock = productClient.searchStock(productId);
        stockCacheService.setData(productId.toString(), dbStock.toString());
        return dbStock;
    }

    // 캐시 재고 증가
    public synchronized void increaseStock(Long productId, Integer quantity) {
        Long newStock = stockCacheService.increase(productId.toString(), quantity);
        productClient.updateStock(productId, newStock.intValue());
    }

    // 캐시 재고 차감
    public synchronized void decreaseStock(Long productId, Integer quantity) {
        Integer currentStock = searchStock(productId);

        if (currentStock - quantity < 0) {
            throw new BaseException(ErrorCode.OUT_OF_STOCK);
        }

        Long newStock = stockCacheService.decrease(productId.toString(), quantity);
        productClient.updateStock(productId, newStock.intValue());
    }

}
