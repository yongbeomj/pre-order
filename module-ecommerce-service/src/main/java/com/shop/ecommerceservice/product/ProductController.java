package com.shop.ecommerceservice.product;

import com.shop.ecommerceservice.common.response.ResponseDto;
import com.shop.ecommerceservice.product.dto.request.ProductCreateRequest;
import com.shop.ecommerceservice.product.dto.response.ProductCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "상품 목록 조회")
    @GetMapping()
    public ResponseDto<List<Product>> search() {
        List<Product> products = productService.searchList();
        return ResponseDto.success(products);
    }

    @Operation(summary = "상품 상세 조회")
    @GetMapping("/{product_id}")
    public ResponseDto<Product> searchDetail(@PathVariable("product_id") Long productId) {
        Product product = productService.searchProductInfo(productId);
        return ResponseDto.success(product);
    }
}
