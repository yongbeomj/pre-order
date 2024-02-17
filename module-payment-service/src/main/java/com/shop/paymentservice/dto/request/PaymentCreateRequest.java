package com.shop.paymentservice.dto.request;

import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.entity.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentCreateRequest {

    private Long orderId;

    public Payment toEntity() {
        return Payment.builder()
                .orderId(this.orderId)
                .paymentType(PaymentType.PENDING)
                .build();
    }

}
