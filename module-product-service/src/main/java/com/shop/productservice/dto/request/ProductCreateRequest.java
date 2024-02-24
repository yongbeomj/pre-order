package com.shop.productservice.dto.request;

import com.shop.productservice.entity.Product;
import com.shop.productservice.entity.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    private String title;
    private String content;
    private Long price;
    private Integer stock;
    private ProductType productType;
    private LocalDateTime reservedAt;

    public Product toEntity() {
        if (this.productType == ProductType.COMMON) {
            this.reservedAt = LocalDateTime.now();
        }

        return Product.builder()
                .title(this.title)
                .content(this.content)
                .price(this.price)
                .stock(this.stock)
                .productType(this.productType)
                .reservedAt(this.reservedAt)
                .build();
    }

}
