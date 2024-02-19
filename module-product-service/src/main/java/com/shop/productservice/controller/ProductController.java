package com.shop.productservice.controller;

import com.shop.productservice.common.response.ResponseDto;
import com.shop.productservice.dto.request.ProductCreateRequest;
import com.shop.productservice.dto.response.ProductResponse;
import com.shop.productservice.dto.response.ProductStockResponse;
import com.shop.productservice.entity.Product;
import com.shop.productservice.entity.ProductType;
import com.shop.productservice.service.ProductService;
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
    public ResponseDto<ProductResponse> create(@RequestBody ProductCreateRequest productCreateRequest) {
        Product product = productService.create(productCreateRequest);
        return ResponseDto.success(ProductResponse.of(product));
    }

    @Operation(summary = "상품 목록 조회")
    @GetMapping()
    public ResponseDto<List<Product>> search(
            @RequestParam(value = "productType", required = false) ProductType productType,
            @RequestParam(value = "timeOffset", required = false) Integer timeOffset
    ) {
        List<Product> products = productService.searchList(productType, timeOffset);
        return ResponseDto.success(products);
    }

    @Operation(summary = "상품 상세 조회")
    @GetMapping("/{product_id}")
    public ResponseDto<ProductResponse> searchDetail(@PathVariable("product_id") Long productId) {
        Product product = productService.searchProductInfo(productId);
        return ResponseDto.success(ProductResponse.of(product));
    }

    @Operation(summary = "상품 재고 조회")
    @GetMapping("/stock/{product_id}")
    public ResponseDto<ProductStockResponse> searchStock(@PathVariable("product_id") Long productId) {
        Product product = productService.searchProductInfo(productId);
        return ResponseDto.success(ProductStockResponse.of(product));
    }

}
