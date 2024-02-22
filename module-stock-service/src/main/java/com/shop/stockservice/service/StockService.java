package com.shop.stockservice.service;

import com.shop.stockservice.client.ProductClient;
import com.shop.stockservice.dto.response.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final ProductClient productClient;
    private final StockCacheService stockCacheService;

    public void searchStockAll() {

        List<StockResponse> stockResponses = productClient.searchAllStock();

        for (StockResponse response : stockResponses) {
            stockCacheService.setData(response.getProductId().toString(), response.getStock().toString());
        }

    }


}
