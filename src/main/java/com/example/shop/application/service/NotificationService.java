package com.example.shop.application.service;

import com.example.shop.application.models.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<Notification> getAllNotifications();
    Optional<Notification> getNotificationById(Long id);
    void saveNotification(Notification notification);
    void deleteNotification(Long id);

    List<Notification> getMyNotifications(Long id);
}
