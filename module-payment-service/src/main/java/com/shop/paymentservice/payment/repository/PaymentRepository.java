package com.shop.paymentservice.payment.repository;

import com.shop.paymentservice.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
