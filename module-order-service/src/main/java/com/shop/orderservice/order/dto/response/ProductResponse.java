package com.shop.orderservice.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long productId;
    private String title;
    private String content;
    private Long price;
    private Integer stock;
    private String productType;
    private LocalDateTime reservedAt;

}
