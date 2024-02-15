package com.shop.productservice.entity;

import com.shop.productservice.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "products")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private boolean reservationStatus;

    private LocalDateTime reservedAt;

    @Builder
    public Product(String title, String content, Long price, Integer stock, boolean reservationStatus, LocalDateTime reservedAt) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.stock = stock;
        this.reservationStatus = reservationStatus;
        this.reservedAt = reservedAt;
    }
}