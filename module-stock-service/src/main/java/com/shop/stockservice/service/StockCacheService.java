package com.shop.stockservice.service;

import com.shop.stockservice.dto.response.StockResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockCacheService {

    private static final String CACHE_KEY_PREFIX = "product:";
    private final RedisTemplate<String, String> redisTemplate;

    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    public void init() {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public String getKey(String productId) {
        return CACHE_KEY_PREFIX + productId;
    }

    public void save(StockResponse response) {
        String key = getKey(response.getProductId().toString());
        String value = response.getStock().toString();

        log.info("Set to Redis {}({})", key, value);
        valueOperations.set(key, value);
    }
}
