package com.shop.productservice.dto.response;

import com.shop.productservice.entity.Product;
import com.shop.productservice.entity.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long productId;
    private String title;
    private String content;
    private Long price;
    private Integer stock;
    private ProductType productType;
    private LocalDateTime reservedAt;

    public static ProductResponse of(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.getStock(),
                product.getProductType(),
                product.getReservedAt()
        );
    }
}
