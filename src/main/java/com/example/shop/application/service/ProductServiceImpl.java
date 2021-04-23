package com.example.shop.application.service;

import com.example.shop.application.models.Product;
import com.example.shop.application.service.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        product.setDate_of_adding(new Date());
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getMyProducts(Long id) {
        return productRepository.findAll().stream().filter(product -> product.getSeller().getId() == id).collect(Collectors.toList());
    }

    @Override
    public List<Product> getMyOrders(Long id) {
        return productRepository.findAll().stream()
                .filter(product -> product.getCustomers().stream().anyMatch(client -> client.getId() == id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getFootwear() {
        return getAllProducts().stream().filter(product -> product.getCategory().equals("FOOTWEAR")).collect(Collectors.toList());
    }

    @Override
    public List<Product> getClothes() {
        return getAllProducts().stream().filter(product -> product.getCategory().equals("CLOTHES")).collect(Collectors.toList());
    }

    @Override
    public List<Product> getAccessories() {
        return getAllProducts().stream().filter(product -> product.getCategory().equals("ACCESSORIES")).collect(Collectors.toList());
    }

    @Override
    public List<Product> getCosmetics() {
        return getAllProducts().stream().filter(product -> product.getCategory().equals("COSMETICS")).collect(Collectors.toList());
    }
}
