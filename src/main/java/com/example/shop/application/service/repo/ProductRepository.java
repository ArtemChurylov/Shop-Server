package com.example.shop.application.service.repo;

import com.example.shop.application.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
