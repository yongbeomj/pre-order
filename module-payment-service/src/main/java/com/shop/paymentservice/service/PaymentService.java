package com.shop.paymentservice.service;

import com.shop.paymentservice.client.OrderClient;
import com.shop.paymentservice.client.ProductClient;
import com.shop.paymentservice.common.exception.BaseException;
import com.shop.paymentservice.common.response.ErrorCode;
import com.shop.paymentservice.dto.request.PaymentCreateRequest;
import com.shop.paymentservice.dto.response.OrderResponse;
import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.entity.PaymentType;
import com.shop.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
    private final ProductClient productClient;

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

        double paymentFailedRate = Math.random();

        // 고객 귀책 사유 시뮬레이션 - 변심 이탈 (20%)
        if (paymentFailedRate < 0.2) {
            handlePaymentFailure(findPayment, PaymentType.CANCELED);
            return findPayment;
        }

        // 고객 귀책 사유 시뮬레이션 - 결제 실패 이탈 (20%)
        if (paymentFailedRate < 0.4) {
            handlePaymentFailure(findPayment, PaymentType.FAILED);
            return findPayment;
        }

        // 결제 처리 완료
        findPayment.setPaymentType(PaymentType.COMPLETED);

        return findPayment;
    }

    private void handlePaymentFailure(Payment payment, PaymentType paymentType) {
        // 주문 정보 조회
        OrderResponse response = orderClient.searchOrder(payment.getOrderId()).getBody();

        // 재고 증가
        productClient.increaseStock(response.getProductId(), response.getQuantity());

        // 결제 상태 변경
        payment.setPaymentType(paymentType);
    }

}
