package com.shop.paymentservice.payment.service;

import com.shop.paymentservice.client.OrderClient;
import com.shop.paymentservice.client.StockClient;
import com.shop.paymentservice.common.exception.BaseException;
import com.shop.paymentservice.common.response.ErrorCode;
import com.shop.paymentservice.payment.dto.request.PaymentCreateRequest;
import com.shop.paymentservice.payment.dto.response.OrderResponse;
import com.shop.paymentservice.payment.entity.Payment;
import com.shop.paymentservice.payment.entity.PaymentType;
import com.shop.paymentservice.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
    private final StockClient stockClient;

    // 결제 생성
    public Payment createPayment(PaymentCreateRequest request) {
        return paymentRepository.save(request.toEntity());
    }

    // 결제 처리
    @Transactional
    public Payment processPayment(Long paymentId) {
        Payment findPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new BaseException(ErrorCode.PAYMENT_NOT_FOUND));

        // 결제 중으로 변경
        findPayment.setPaymentType(PaymentType.PROCESSING);

        // 고객 귀책 사유 시뮬레이션 - 결제 실패 이탈 (20%)
        if (Math.random() <= 0.2) {
            handlePaymentFailure(findPayment, PaymentType.FAILED);
            return findPayment;
        }

        // 결제 처리 완료
        findPayment.setPaymentType(PaymentType.COMPLETED);

        return findPayment;
    }

    // 결제 취소
    @Transactional
    public Payment cancelPayment(Long paymentId) {
        Payment findPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new BaseException(ErrorCode.PAYMENT_NOT_FOUND));

        handlePaymentFailure(findPayment, PaymentType.CANCELED);
        return findPayment;
    }

    private void handlePaymentFailure(Payment payment, PaymentType paymentType) {
        // 주문 정보 조회
        OrderResponse response = orderClient.searchOrder(payment.getOrderId()).getBody();

        // 재고 증가
        stockClient.increaseStock(response.getProductId(), response.getQuantity());

        // 결제 상태 변경
        payment.setPaymentType(paymentType);
    }

}
