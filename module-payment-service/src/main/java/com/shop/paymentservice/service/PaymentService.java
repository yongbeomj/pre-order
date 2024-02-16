package com.shop.paymentservice.service;

import com.shop.paymentservice.client.OrderClient;
import com.shop.paymentservice.dto.request.PaymentCreateRequest;
import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.entity.PaymentType;
import com.shop.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

    // 결제 생성
    public Payment createPayment(PaymentCreateRequest request) {
        Long createOrderId = createOrder(request.getProductId(), request.getQuantity());

        Payment createPayment = Payment.builder()
                .userId(request.getUserId())
                .orderId(createOrderId)
                .paymentType(PaymentType.PENDING)
                .build();

        return paymentRepository.save(createPayment);
    }

    // 주문 생성
    public Long createOrder(Long productId, Integer quantity) {
        return orderClient.createOrder(productId, quantity).getBody();
    }
}
