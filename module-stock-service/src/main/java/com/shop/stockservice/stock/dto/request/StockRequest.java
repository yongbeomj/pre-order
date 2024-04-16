package com.shop.stockservice.stock.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockRequest {

    private Long productId;
    private Integer quantity;

}
