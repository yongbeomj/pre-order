package com.shop.paymentservice.dto.response;

import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreateResponse {

    private Long orderId;
    private PaymentType paymentType;

    public static PaymentCreateResponse of(Payment payment) {
        return new PaymentCreateResponse(
                payment.getOrderId(),
                payment.getPaymentType()
        );
    }

}
