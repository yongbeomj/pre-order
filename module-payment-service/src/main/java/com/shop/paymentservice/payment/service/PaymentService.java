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
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
    private final StockClient stockClient;
    private final RedissonClient redissonClient;

    // 결제 생성
    public Payment createPayment(PaymentCreateRequest request) {
        return paymentRepository.save(request.toEntity());
    }

    // 결제 처리
    @Transactional
    public Payment processPayment(Long paymentId) {
        RLock lock = redissonClient.getLock(paymentId.toString());

        try {
            if (!lock.tryLock(10, 1, TimeUnit.SECONDS)) {
                throw new BaseException(ErrorCode.FAIL_TO_ACQUIRE_LOCK);
            }

            Payment findPayment = paymentRepository.findById(paymentId)
                    .orElseThrow(() -> new BaseException(ErrorCode.PAYMENT_NOT_FOUND));

            // 고객 귀책 사유 시뮬레이션 - 결제 실패 이탈 (20%)
            if (Math.random() <= 0.2) {
                increaseStock(findPayment.getOrderId());
                findPayment.updatePaymentType(PaymentType.FAILED);
            } else {
                findPayment.updatePaymentType(PaymentType.COMPLETED);
            }

            return paymentRepository.save(findPayment);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    // 결제 취소
    @Transactional
    public Payment cancelPayment(Long paymentId) {
        Payment findPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new BaseException(ErrorCode.PAYMENT_NOT_FOUND));

        increaseStock(findPayment.getOrderId());
        findPayment.updatePaymentType(PaymentType.CANCELED);

        return findPayment;
    }

    @Transactional
    public void increaseStock(Long orderId) {
        OrderResponse response = orderClient.searchOrder(orderId).getBody();
        stockClient.increaseStock(response.getProductId(), response.getQuantity());
    }

}
