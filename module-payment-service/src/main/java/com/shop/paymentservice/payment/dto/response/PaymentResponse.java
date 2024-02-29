package com.shop.paymentservice.payment.dto.response;

import com.shop.paymentservice.payment.entity.Payment;
import com.shop.paymentservice.payment.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long paymentId;
    private Long orderId;
    private PaymentType paymentType;

    public static PaymentResponse of(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getPaymentType()
        );
    }
}
