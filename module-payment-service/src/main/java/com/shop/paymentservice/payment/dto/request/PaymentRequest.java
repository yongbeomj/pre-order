package com.shop.paymentservice.payment.dto.request;

import com.shop.paymentservice.payment.entity.Payment;
import com.shop.paymentservice.payment.entity.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {

    private Long orderId;
    private PaymentType paymentType;

    public Payment toEntity(Long orderId, PaymentType paymentType) {
        return Payment.builder()
                .orderId(orderId)
                .paymentType(paymentType)
                .build();
    }

}
