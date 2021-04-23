package com.example.shop.application.models;

import com.example.shop.security.models.Client;
import com.example.shop.security.models.Seller;

import javax.persistence.*;
import java.util.Date;
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

    @Column(name = "date_of_adding")
    private Date date_of_adding;

    @Lob
    @Column(name = "image")
    private String image;

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

    public void setCustomers(Set<Client> customers) {
        this.customers = customers;
    }

    public Set<Client> getCustomers() {
        for (Client c : customers) {
            c.setOrders(null);
        }
        return customers;
    }

    public Seller getSeller() {
        seller.setProducts(null);
        seller.setNotifications(null);
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Date getDate_of_adding() {
        return date_of_adding;
    }

    public void setDate_of_adding(Date date_of_adding) {
        this.date_of_adding = date_of_adding;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
