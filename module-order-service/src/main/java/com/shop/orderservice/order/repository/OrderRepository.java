package com.shop.orderservice.order.repository;

import com.shop.orderservice.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
