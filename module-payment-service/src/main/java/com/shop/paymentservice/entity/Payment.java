package com.shop.paymentservice.entity;

import com.shop.paymentservice.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "payments")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // 구매자

    @Column(nullable = false)
    private Long orderId; // 주문 정보

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; // 결제 상태

    @Builder
    public Payment(Long userId, Long orderId, PaymentStatus paymentStatus) {
        this.userId = userId;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
    }
}
