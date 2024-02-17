package com.shop.paymentservice.service;

import com.shop.paymentservice.dto.request.PaymentCreateRequest;
import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // 결제 생성 - 결제 프로세스 진입 시 대기 상태로 결제 건 생성
    public Payment createPayment(PaymentCreateRequest request) {
        return paymentRepository.save(request.toEntity());
    }

}
