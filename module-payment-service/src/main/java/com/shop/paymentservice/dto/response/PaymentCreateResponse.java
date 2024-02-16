package com.shop.paymentservice.dto.response;

import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCreateResponse {

    private Long userId;
    private Long orderId;
    private PaymentStatus paymentStatus; // 결제 상태

    public static PaymentCreateResponse of(Payment payment) {
        return new PaymentCreateResponse(
                payment.getUserId(),
                payment.getOrderId(),
                payment.getPaymentStatus()
        );
    }

}
