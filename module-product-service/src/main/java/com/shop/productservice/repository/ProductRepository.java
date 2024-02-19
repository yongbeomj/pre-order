package com.shop.productservice.repository;

import com.shop.productservice.entity.Product;
import com.shop.productservice.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByProductType(ProductType productType);
    List<Product> findByProductTypeAndReservedAtBetween(ProductType productType, LocalDateTime startDatetime, LocalDateTime endDatetime);
}
