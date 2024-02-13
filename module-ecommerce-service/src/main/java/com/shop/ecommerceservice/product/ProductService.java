package com.shop.ecommerceservice.product;

import com.shop.ecommerceservice.common.exception.BaseException;
import com.shop.ecommerceservice.common.response.ErrorCode;
import com.shop.ecommerceservice.product.dto.request.ProductCreateRequest;
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
    public Product create(ProductCreateRequest productCreateRequest) {
        return productRepository.save(productCreateRequest.toEntity());
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


}
