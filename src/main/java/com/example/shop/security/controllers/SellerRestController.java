package com.example.shop.security.controllers;

import com.example.shop.security.models.Seller;
import com.example.shop.security.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service/seller")
public class SellerRestController {

    private final SellerService sellerService;

    public SellerRestController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    // Same as in client Controller. Reduce the objects and returns all sellers
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();

        for (Seller s : sellers) {
            s.setProducts(null);
            s.setNotifications(null);
        }

        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    // Returns seller by id
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        Optional<Seller> seller = sellerService.getSellerById(id);
        if (seller.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        seller.get().setProducts(null);
        seller.get().setNotifications(null);
        return new ResponseEntity<>(seller.get(), HttpStatus.OK);
    }

    // Save seller
    @PostMapping
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) {
        if (seller == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        sellerService.saveSeller(seller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Don`t use this method, buy if I`ll need, I can modify it
    @PutMapping
    public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller) {
        if (seller == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        sellerService.saveSeller(seller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete seller
    @DeleteMapping("/{id}")
    public ResponseEntity<Seller> deleteSellerById(@PathVariable Long id) {
        Optional<Seller> seller = sellerService.getSellerById(id);
        if (seller.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        sellerService.deleteSellerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}














