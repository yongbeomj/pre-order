package com.shop.ecommerceservice.product;

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


}
