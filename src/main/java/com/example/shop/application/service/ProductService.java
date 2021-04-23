package com.example.shop.application.service;


import com.example.shop.application.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void saveProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    void deleteProductById(Long id);
    List<Product> getMyProducts(Long id);

    List<Product> getMyOrders(Long id);

    List<Product> getFootwear();
    List<Product> getClothes();
    List<Product> getAccessories();
    List<Product> getCosmetics();
}

