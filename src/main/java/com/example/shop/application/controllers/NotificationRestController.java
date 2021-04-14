package com.example.shop.application.controllers;

import com.example.shop.application.models.Notification;
import com.example.shop.application.service.NotificationService;
import com.example.shop.security.models.Seller;
import com.example.shop.security.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service/notification")
public class NotificationRestController {

    private final NotificationService notificationService;
    private final SellerService sellerService;

    public NotificationRestController(NotificationService notificationService, SellerService sellerService) {
        this.notificationService = notificationService;
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return new ResponseEntity<>(notificationService.getAllNotifications(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Notification> saveNotification(@RequestBody Notification notification) {
        if (notification == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Seller seller = sellerService.getSellerById(notification.getSeller().getId()).orElseThrow();
        seller.getNotifications().add(notification);
        notificationService.saveNotification(notification);
        sellerService.saveSeller(seller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable Long id) {
        Optional<Notification> notification = notificationService.getNotificationById(id);
        if (notification.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
