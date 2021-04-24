package com.example.shop.application.models;

import com.example.shop.security.models.Seller;

import javax.persistence.*;


// Notification model which seller gets after client buy his product
@Entity
public class Notification {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id")
    @SequenceGenerator(name = "notification_id", sequenceName = "notification_id", allocationSize = 1)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Notification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Seller getSeller() {
        seller.setProducts(null);
        seller.setNotifications(null);
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
