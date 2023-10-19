package com.zhehao.fishing.notification.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhehao.fishing.model.*;

import java.util.List;

public interface NotificationService extends IService<NotificationEntity> {

    NotificationEntity buildNotification(CatchLikeEntity catchLikeEntity);
    NotificationEntity buildNotification(PostLikeEntity postLikeEntity);
    NotificationEntity buildNotification(PostCommentEntity postCommentEntity);
    void sendNotification(NotificationEntity notificationEntity);
    NotificationEntity getNotificationById(Long id);
    void markAsRead(Long id);
    void deleteNotificationById(Long id);
    List<NotificationEntity> getNotificationsByUserIdOrderedByTime(Long userId);
}
