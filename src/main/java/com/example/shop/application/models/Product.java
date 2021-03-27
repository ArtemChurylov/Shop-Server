package com.example.shop.application.models;

import com.example.shop.security.models.Client;
import com.example.shop.security.models.Seller;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")
    @SequenceGenerator(name = "product_id", sequenceName = "product_id", allocationSize = 1)
    private Long id;

    @Column(name = "product_title")
    private String title;

    @Column(name = "product_price")
    private Integer price;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_category")
    private String category;

    @ManyToMany
    @JoinTable(
            name = "product_client",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    )
    private Set<Client> customers;

    @ManyToOne
    @JoinTable(
            name = "product_seller",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    )
    private Seller seller;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Client> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Client> customers) {
        this.customers = customers;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}