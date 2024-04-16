package com.shop.stockservice.stock.service;

import com.shop.stockservice.client.ProductClient;
import com.shop.stockservice.common.exception.BaseException;
import com.shop.stockservice.common.response.ErrorCode;
import com.shop.stockservice.stock.dto.response.StockResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductClient productClient;
    private final StockCacheService stockCacheService;
    private final RedissonClient redissonClient;

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
    public Integer searchStock(Long productId) {
        RLock lock = redissonClient.getLock(productId.toString());

        try {
            if (!lock.tryLock(10, 1, TimeUnit.SECONDS)) {
                throw new BaseException(ErrorCode.FAIL_TO_ACQUIRE_LOCK);
            }

            // redis 조회
            String cacheStock = stockCacheService.getData(productId.toString());
            if (cacheStock != null) {
                return Integer.parseInt(cacheStock);
            }

            // DB 조회 (캐시 없을 경우)
            Integer dbStock = productClient.searchStock(productId);
            stockCacheService.setData(productId.toString(), dbStock.toString());
            return dbStock;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    // 캐시 재고 증가
    public Long increaseStock(Long productId, Integer quantity) {
        return stockCacheService.increase(productId.toString(), quantity);
    }

    // 캐시 재고 차감
    public Long decreaseStock(Long productId, Integer quantity) {
        return stockCacheService.decrease(productId.toString(), quantity);
    }

    // 재고 업데이트
    public void updateStock(Long productId, Integer quantity, String code) {
        RLock lock = redissonClient.getLock(productId.toString());

        try {
            if (!lock.tryLock(10, 3, TimeUnit.SECONDS)) {
                throw new BaseException(ErrorCode.FAIL_TO_ACQUIRE_LOCK);
            }

            Long newStock;

            if (code.equals("incr")) {
                newStock = stockCacheService.increase(productId.toString(), quantity);
            } else {
                Integer currentStock = searchStock(productId);

                if (currentStock - quantity >= 0) {
                    newStock = stockCacheService.decrease(productId.toString(), quantity);
                } else {
                    throw new BaseException(ErrorCode.OUT_OF_STOCK);
                }
            }

            productClient.updateStock(productId, newStock.intValue());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
