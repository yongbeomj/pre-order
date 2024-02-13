package com.shop.ecommerceservice.product;

import com.shop.ecommerceservice.product.dto.request.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    // 상품 등록
    @Transactional
    public Product create(ProductCreateRequest productCreateRequest) {
        return productRepository.save(productCreateRequest.toEntity());
    }


}
