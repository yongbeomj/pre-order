package com.shop.productservice.entity;

import com.shop.productservice.common.entity.BaseTimeEntity;
import com.shop.productservice.common.exception.BaseException;
import com.shop.productservice.common.response.ErrorCode;
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

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private LocalDateTime reservedAt;

    @Builder
    public Product(String title, String content, Long price, Integer stock, ProductType productType, LocalDateTime reservedAt) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.stock = stock;
        this.productType = productType;
        this.reservedAt = reservedAt;
    }

    // 재고 증가
    public void increaseStock(Integer quantity) {
        this.stock += quantity;
    }

    // 재고 감소
    public void decreaseStock(Integer quantity) {
        if (this.stock < quantity) {
            throw new BaseException(ErrorCode.OUT_OF_STOCK);
        }

        this.stock -= quantity;
    }
}
