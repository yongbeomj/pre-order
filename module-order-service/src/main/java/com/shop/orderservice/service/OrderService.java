package com.shop.orderservice.service;

import com.shop.orderservice.client.ProductClient;
import com.shop.orderservice.client.StockClient;
import com.shop.orderservice.common.exception.BaseException;
import com.shop.orderservice.common.response.ErrorCode;
import com.shop.orderservice.dto.request.OrderCreateRequest;
import com.shop.orderservice.dto.response.ProductResponse;
import com.shop.orderservice.entity.Order;
import com.shop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final StockClient stockClient;

    // 주문 생성
    public Order createOrder(OrderCreateRequest request) {
        // 재고 부족 여부 확인
        validateOrder(request.getProductId(), request.getQuantity());

        // 재고 차감
        stockClient.decreaseStock(request.getProductId(), request.getQuantity());

        return orderRepository.save(request.toEntity());
    }

    // 재고 부족 여부 확인
    public void validateOrder(Long productId, Integer quantity) {
        ProductResponse response = productClient.searchProduct(productId);
        LocalDateTime openAt = response.getReservedAt();

        if (openAt != null && openAt.isAfter(LocalDateTime.now())) {
            throw new BaseException(ErrorCode.ORDER_NOT_AVAILABLE);
        }

        Integer stock = stockClient.searchStock(productId);

        if (stock < quantity) {
            throw new BaseException(ErrorCode.OUT_OF_STOCK);
        }
    }

    // 주문 단건 조회
    public Order searchOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new BaseException(ErrorCode.ORDER_NOT_FOUND));
    }

}
