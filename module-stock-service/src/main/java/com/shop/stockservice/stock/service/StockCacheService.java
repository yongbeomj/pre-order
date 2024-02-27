package com.shop.stockservice.stock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockCacheService {

    private static final String CACHE_KEY_PREFIX = "product:";
    private static final Duration CACHE_TTL = Duration.ofDays(3);

    private final RedisTemplate<String, String> redisTemplate;

    // 캐시 저장
    public void setData(String productId, String stock) {
        try {
            log.info("[Save data to redis success] {} ({})", generateKey(productId), stock);
            redisTemplate.opsForValue().set(generateKey(productId), stock, CACHE_TTL);
        } catch (Exception e) {
            log.info("[Save data to redis error] {}", e.getMessage());
        }
    }

    // 캐시 조회
    public String getData(String productId) {
        try {
            log.info("[Get data to redis success] {}", generateKey(productId));
            return redisTemplate.opsForValue().get(generateKey(productId));
        } catch (Exception e) {
            log.info("[Get data to redis error] {}", e.getMessage());
            return null;
        }
    }

    // 캐시 삭제
    public void deleteData(String productId) {
        redisTemplate.delete(generateKey(productId));
    }

    // 값 증가
    public Long increase(String productId, Integer quantity) {
        return redisTemplate.opsForValue().increment(generateKey(productId), quantity);
    }

    public Long decrease(String productId, Integer quantity) {
        return redisTemplate.opsForValue().decrement(generateKey(productId), quantity);
    }

    // 캐시 키 생성 (접두어 포함)
    public String generateKey(String productId) {
        return CACHE_KEY_PREFIX + productId;
    }

}
