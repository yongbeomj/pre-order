package com.shop.paymentservice.dto.response;

import com.shop.paymentservice.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private Long id;

    public static PaymentResponse of(Payment payment) {
        return new PaymentResponse(
                payment.getId()
        );
    }
}
