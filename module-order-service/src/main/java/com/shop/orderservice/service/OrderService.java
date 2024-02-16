package com.shop.orderservice.service;

import com.shop.orderservice.client.ProductClient;
import com.shop.orderservice.common.exception.BaseException;
import com.shop.orderservice.common.response.ErrorCode;
import com.shop.orderservice.entity.Order;
import com.shop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    // 주문 생성
    public Order createOrder(Long productId, Integer quantity) {
        // 재고 부족 여부 확인
        checkProductStock(productId, quantity);

        Order saveOrder = Order.builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        return orderRepository.save(saveOrder);
    }

    // 재고 부족여부 확인 및 차감
    public void checkProductStock(Long productId, Integer quantity) {
        Integer stock = productClient.searchStock(productId).getBody();

        if (stock < quantity) {
            throw new BaseException(ErrorCode.OUT_OF_STOCK);
        }

        // 재고 차감
        productClient.decreaseStock(productId, quantity);
    }

}
