package com.shop.orderservice.service;

import com.shop.orderservice.entity.Order;
import com.shop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
//    private final ProductClient productClient;


    public Order create(Long productId, Integer quantity) {
        // 재고 확인
//        productClient.searchStock(productId);

        // 재고 차감

        // 주문 저장
        Order saveOrder = Order.builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        return orderRepository.save(saveOrder);
    }
}
