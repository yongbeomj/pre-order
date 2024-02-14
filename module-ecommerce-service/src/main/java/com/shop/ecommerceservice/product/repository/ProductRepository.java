package com.shop.ecommerceservice.product.repository;

import com.shop.ecommerceservice.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
