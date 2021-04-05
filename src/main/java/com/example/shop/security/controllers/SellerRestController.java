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

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();

        for (Seller s : sellers) {
            s.setProducts(null);
        }

        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        Optional<Seller> seller = sellerService.getSellerById(id);
        if (seller.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        seller.get().setProducts(null);
        return new ResponseEntity<>(seller.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) {
        if (seller == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        sellerService.saveSeller(seller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller) {
        if (seller == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        sellerService.saveSeller(seller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seller> deleteSellerById(@PathVariable Long id) {
        Optional<Seller> seller = sellerService.getSellerById(id);
        if (seller.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        sellerService.deleteSellerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}














