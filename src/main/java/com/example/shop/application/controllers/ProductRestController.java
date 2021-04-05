package com.example.shop.application.controllers;

import com.example.shop.application.models.Product;
import com.example.shop.application.service.ProductService;
import com.example.shop.security.models.Seller;
import com.example.shop.security.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service/product")
public class ProductRestController {

    private final ProductService productService;
    private final SellerService sellerService;

    public ProductRestController(ProductService productService, SellerService sellerService) {
        this.productService = productService;
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        if (product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Seller seller = sellerService.getSellerById(product.getSeller().getId()).orElseThrow();
        seller.getProducts().add(product);
        sellerService.saveSeller(seller);
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}














