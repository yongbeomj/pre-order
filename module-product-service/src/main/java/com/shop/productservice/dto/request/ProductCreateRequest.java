package com.shop.productservice.dto.request;

import com.shop.productservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    private String title;
    private String content;
    private Long price;
    private Integer stock;
    private boolean reservationStatus;
    private LocalDateTime reservedAt;

    public Product toEntity() {
        return Product.builder()
                .title(this.title)
                .content(this.content)
                .price(this.price)
                .stock(this.stock)
                .reservationStatus(this.reservationStatus)
                .reservedAt(this.reservedAt)
                .build();
    }

}
