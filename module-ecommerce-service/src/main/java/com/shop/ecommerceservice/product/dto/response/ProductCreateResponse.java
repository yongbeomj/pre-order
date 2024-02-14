package com.shop.ecommerceservice.product.dto.response;

import com.shop.ecommerceservice.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateResponse {

    private String title;
    private String content;
    private Long price;
    private boolean reservationStatus;
    private LocalDateTime reservedAt;

    public static ProductCreateResponse of(Product product) {
        return new ProductCreateResponse(
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.isReservationStatus(),
                product.getReservedAt()
        );
    }
}
