package com.shop.paymentservice.controller;

import com.shop.paymentservice.common.response.ResponseDto;
import com.shop.paymentservice.dto.request.PaymentCreateRequest;
import com.shop.paymentservice.dto.response.PaymentCreateResponse;
import com.shop.paymentservice.dto.response.PaymentResponse;
import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제 프로세스 진입")
    @PostMapping("/enter")
    public ResponseDto<PaymentCreateResponse> paymentProcessEnter(@RequestBody PaymentCreateRequest request) {
        Payment createPayment = paymentService.createPayment(request);

        return ResponseDto.success(PaymentCreateResponse.of(createPayment));
    }

    @Operation(summary = "결제 처리")
    @PostMapping("/progress/{payment_id}")
    public ResponseDto<PaymentResponse> processPayment(@PathVariable("payment_id") Long paymentId) {
        Payment payment = paymentService.processPayment(paymentId);

        return ResponseDto.success(PaymentResponse.of(payment));
    }

    @Operation(summary = "결제 취소")
    @PostMapping("/cancel/{payment_id}")
    public ResponseDto<PaymentResponse> cancelPayment(@PathVariable("payment_id") Long paymentId) {
        Payment payment = paymentService.cancelPayment(paymentId);

        return ResponseDto.success(PaymentResponse.of(payment));
    }

}
