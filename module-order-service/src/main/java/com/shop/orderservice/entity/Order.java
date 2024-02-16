package com.shop.orderservice.entity;

import com.shop.orderservice.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId; // 상품 정보

    @Column(nullable = false)
    private Integer quantity; // 주문 수량

    @Builder
    public Order(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
