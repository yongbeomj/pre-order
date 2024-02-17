package com.shop.paymentservice.dto.response;

import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long id;
    private PaymentType paymentType;

    public static PaymentResponse of(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getPaymentType()
        );
    }
}
