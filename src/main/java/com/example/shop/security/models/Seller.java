package com.example.shop.security.models;

import com.example.shop.application.models.Notification;
import com.example.shop.application.models.Product;

import javax.persistence.*;
import java.util.List;


// Seller model
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @Column(name = "seller_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_id")
    @SequenceGenerator(name = "seller_id", sequenceName = "seller_id", allocationSize = 1)
    private Long id;

    @Column(name = "seller_email")
    private String email;

    @Column(name = "seller_name")
    private String name;

    @Column(name = "seller_surname")
    private String surname;

    @Column(name = "seller_password")
    private String password;

    @Column(name = "seller_phone")
    private String phone;

    @Column(name = "seller_role")
    private String role;

    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    @OneToMany(mappedBy = "seller")
    private List<Notification> notifications;

    @Column(name = "notification_count")
    private Integer notificationCount;

    public Seller() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void setNotificationCount(Integer notificationCount) {
        this.notificationCount = notificationCount;
    }

    public Integer getNotificationCount() {
        if(notificationCount == null) return 0;
        return notificationCount;
    }
}
