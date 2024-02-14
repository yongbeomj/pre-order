package com.shop.ecommerceservice.product.dto.response;

import com.shop.ecommerceservice.product.entity.Product;
import com.shop.ecommerceservice.product.entity.ProductStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockResponse {

    private Long id;
    private String title;
    private int stock;

    public static ProductStockResponse of(Product product, ProductStock productStock) {
        return new ProductStockResponse(
                product.getId(),
                product.getTitle(),
                productStock.getStock()
        );
    }
}
