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

    // Returns all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // Returns product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }


    // Add this product to the seller and saves it
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

    // If such product already exist -> updates it, else save
    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        productService.saveProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete product by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Returns all products which seller has created
    @GetMapping("/{id}/myProducts")
    public ResponseEntity<List<Product>> getSellerProducts(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getMyProducts(id), HttpStatus.OK);
    }

    // Returns all products which client has bought
    @GetMapping("/{id}/myOrders")
    public ResponseEntity<List<Product>> getClientOrders(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getMyOrders(id), HttpStatus.OK);
    }

    // Returns all products where category == footwear
    @GetMapping("/footwear")
    public ResponseEntity<List<Product>> getAllFootwear() {
        return new ResponseEntity<>(productService.getFootwear(), HttpStatus.OK);
    }

    // Returns all products where category == clothes
    @GetMapping("/clothes")
    public ResponseEntity<List<Product>> getAllClothes() {
        return new ResponseEntity<>(productService.getClothes(), HttpStatus.OK);
    }

    // Returns all products where category == accessories
    @GetMapping("/accessories")
    public ResponseEntity<List<Product>> getAllAccessories() {
        return new ResponseEntity<>(productService.getAccessories(), HttpStatus.OK);
    }

    // Returns all products where category == cosmetics
    @GetMapping("/cosmetics")
    public ResponseEntity<List<Product>> getAllCosmetics() {
        return new ResponseEntity<>(productService.getCosmetics(), HttpStatus.OK);
    }
}














