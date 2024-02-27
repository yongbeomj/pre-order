package com.shop.productservice.product.dto.response;

import com.shop.productservice.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockResponse {

    private Long productId;
    private Integer stock;

    public static ProductStockResponse of(Product product) {
        return new ProductStockResponse(
                product.getId(),
                product.getStock()
        );
    }
}
