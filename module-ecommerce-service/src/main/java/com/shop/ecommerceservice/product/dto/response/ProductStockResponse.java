package com.shop.ecommerceservice.product.dto.response;

import com.shop.ecommerceservice.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockResponse {

    private Long id;
    private String title;
    private Integer stock;

    public static ProductStockResponse of(Product product) {
        return new ProductStockResponse(
                product.getId(),
                product.getTitle(),
                product.getStock()
        );
    }
}
