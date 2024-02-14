package com.shop.ecommerceservice.product.service;

import com.shop.ecommerceservice.common.exception.BaseException;
import com.shop.ecommerceservice.common.response.ErrorCode;
import com.shop.ecommerceservice.product.entity.Product;
import com.shop.ecommerceservice.product.entity.ProductStock;
import com.shop.ecommerceservice.product.dto.request.ProductCreateRequest;
import com.shop.ecommerceservice.product.repository.ProductRepository;
import com.shop.ecommerceservice.product.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;
    public final ProductStockRepository productStockRepository;

    // 상품 등록
    @Transactional
    public Product create(ProductCreateRequest request) {
        Product savedProduct = productRepository.save(request.toEntity());

        ProductStock productStock = ProductStock.builder()
                .stock(request.getStock())
                .productId(savedProduct.getId())
                .build();

        productStockRepository.save(productStock);

        return savedProduct;
    }

    // 상품 목록 조회
    public List<Product> searchList() {
        return productRepository.findAll();
    }

    // 상품 상세 조회
    public Product searchProductInfo(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    // 상품 재고 조회
    public ProductStock getStock(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(ErrorCode.PRODUCT_NOT_FOUND));

        return productStockRepository.findByProductId(productId).get();
    }

}
