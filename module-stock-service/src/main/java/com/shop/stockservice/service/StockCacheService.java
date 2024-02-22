package com.shop.stockservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockCacheService {

    private static final String CACHE_KEY_PREFIX = "product:";
    private final RedisTemplate<String, String> redisTemplate;

    // 캐시 저장
    public void setData(String productId, String stock) {
        try {
            log.info("[Save redis data success] {} ({})", getKey(productId), stock);
            redisTemplate.opsForValue().set(getKey(productId), stock);
        } catch (Exception e) {
            log.info("[Save redis data error] {}", e.getMessage());
        }
    }

    // 캐시 키 생성 (접두어 포함)
    public String getKey(String productId) {
        return CACHE_KEY_PREFIX + productId;
    }

}
