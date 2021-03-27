package com.example.shop.security.service;

import com.example.shop.security.models.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {
    void saveSeller(Seller seller);
    List<Seller> getAllSellers();
    Optional<Seller> getSellerById(Long id);
    void deleteSellerById(Long id);
}
