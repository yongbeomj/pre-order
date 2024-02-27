package com.shop.paymentservice.payment.dto.request;

import com.shop.paymentservice.payment.entity.Payment;
import com.shop.paymentservice.payment.entity.PaymentType;
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
