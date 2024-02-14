package com.shop.ecommerceservice.product.dto.response;

import com.shop.ecommerceservice.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String title;
    private String content;
    private Long price;
    private Integer stock;
    private boolean reservationStatus;
    private LocalDateTime reservedAt;

    public static ProductResponse of(Product product) {
        return new ProductResponse(
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.getStock(),
                product.isReservationStatus(),
                product.getReservedAt()
        );
    }
}
