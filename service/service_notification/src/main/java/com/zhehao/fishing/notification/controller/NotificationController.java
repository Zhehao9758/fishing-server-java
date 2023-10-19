package com.zhehao.fishing.notification.controller;

import com.zhehao.fishing.model.NotificationEntity;
import com.zhehao.fishing.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationEntity> getNotificationById(@PathVariable Long id){
        NotificationEntity notificationEntity = notificationService.getNotificationById(id);
        if (notificationEntity != null) {
            return ResponseEntity.ok(notificationEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotificationById(@PathVariable Long id){
        NotificationEntity notificationEntity = notificationService.getNotificationById(id);
        if (notificationEntity != null) {
            notificationService.deleteNotificationById(id);
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> markAsRead(Long notificationId){
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/myNotifications")
    public ResponseEntity<List<NotificationEntity>> getNotificationsByUserIdOrderedByTime(@RequestParam("userId") Long user_id){
        return ResponseEntity.ok(notificationService.getNotificationsByUserIdOrderedByTime(user_id));
    }








}
