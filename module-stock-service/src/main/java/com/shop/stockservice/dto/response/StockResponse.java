package com.shop.stockservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {

    private Long productId;
    private Integer stock;

    public static StockResponse of(Long productId, Integer stock) {
        return new StockResponse(productId, stock);
    }
}
