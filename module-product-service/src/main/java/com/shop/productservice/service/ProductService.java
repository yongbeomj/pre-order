package com.shop.productservice.service;

import com.shop.productservice.common.exception.BaseException;
import com.shop.productservice.common.response.ErrorCode;
import com.shop.productservice.dto.request.ProductCreateRequest;
import com.shop.productservice.entity.Product;
import com.shop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    // 상품 등록
    @Transactional
    public Product create(ProductCreateRequest request) {
        return productRepository.save(request.toEntity());
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

    // 재고 증가
    @Transactional
    public void increaseStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(ErrorCode.PRODUCT_NOT_FOUND));

        product.increaseStock(quantity);
    }

    // 재고 감소
    @Transactional
    public void decreaseStock(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(ErrorCode.PRODUCT_NOT_FOUND));

        product.decreaseStock(quantity);
    }

}
