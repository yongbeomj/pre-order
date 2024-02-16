package com.shop.paymentservice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentCreateRequest {

    private Long userId;
    private Long productId;
    private Integer quantity;

}
