package com.shop.paymentservice.controller;

import com.shop.paymentservice.common.response.ResponseDto;
import com.shop.paymentservice.dto.request.PaymentCreateRequest;
import com.shop.paymentservice.dto.response.PaymentCreateResponse;
import com.shop.paymentservice.entity.Payment;
import com.shop.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Operation(summary = "결제 진행")
    @PostMapping("/progress")
    public ResponseEntity<?> payment() {
        return ResponseEntity.ok("");
    }

}
