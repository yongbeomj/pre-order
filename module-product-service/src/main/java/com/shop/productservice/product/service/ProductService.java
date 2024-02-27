package com.shop.productservice.product.service;

import com.shop.productservice.client.StockClient;
import com.shop.productservice.common.exception.BaseException;
import com.shop.productservice.common.response.ErrorCode;
import com.shop.productservice.product.dto.request.ProductCreateRequest;
import com.shop.productservice.product.entity.Product;
import com.shop.productservice.product.entity.ProductType;
import com.shop.productservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;
    public final StockClient stockClient;

    // 상품 등록
    @Transactional
    public Product create(ProductCreateRequest request) {
        return productRepository.save(request.toEntity());
    }

    // 상품 목록 조회
    public List<Product> searchList(ProductType productType, Integer timeOffset) {

        // 전체 조회 (일반 + 예약)
        if (productType == null) {
            return productRepository.findAll();
        }

        // 일반 상품 조회
        if (productType == ProductType.COMMON) {
            return productRepository.findAllByProductType(productType);
        }

        // 예약 상품 조회
        if (timeOffset == null) {
            return productRepository.findAllByProductType(productType);
        } else {
            LocalDateTime startDatetime = LocalDateTime.now().plusMinutes(timeOffset);
            LocalDateTime endDatetime = startDatetime.plusMinutes(1);

            return productRepository.findByProductTypeAndReservedAtBetween(productType, startDatetime, endDatetime);
        }

    }

    // 상품 상세 조회
    public Product searchProductInfo(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    // 재고 전체 조회
    public List<Product> searchAllStock() {
        return productRepository.findAll();
    }

    // 재고 업데이트
    @Transactional
    public void updateStock(Long productId, Integer newStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(ErrorCode.PRODUCT_NOT_FOUND));

        product.updateStock(newStock);
    }
}
