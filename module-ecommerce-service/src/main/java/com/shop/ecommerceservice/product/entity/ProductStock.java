package com.shop.ecommerceservice.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "product_stocks")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Long productId;

    @Builder
    public ProductStock(Integer stock, Long productId) {
        this.stock = stock;
        this.productId = productId;
    }
}
