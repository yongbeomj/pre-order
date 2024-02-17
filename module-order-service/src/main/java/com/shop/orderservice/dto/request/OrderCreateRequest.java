package com.shop.orderservice.dto.request;

import com.shop.orderservice.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    private Long userId;
    private Long productId;
    private Integer quantity;

    public Order toEntity() {
        return Order.builder()
                .userId(this.getUserId())
                .productId(this.getProductId())
                .quantity(this.getQuantity())
                .build();
    }

}
