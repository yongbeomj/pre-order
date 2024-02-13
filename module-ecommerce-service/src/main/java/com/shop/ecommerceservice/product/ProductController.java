package com.shop.ecommerceservice.product;

import com.shop.ecommerceservice.common.response.ResponseDto;
import com.shop.ecommerceservice.product.dto.request.ProductCreateRequest;
import com.shop.ecommerceservice.product.dto.response.ProductCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록")
    @PostMapping("/create")
    public ResponseDto<ProductCreateResponse> create(@RequestBody ProductCreateRequest productCreateRequest) {
        Product product = productService.create(productCreateRequest);
        return ResponseDto.success(ProductCreateResponse.of(product));
    }

}
