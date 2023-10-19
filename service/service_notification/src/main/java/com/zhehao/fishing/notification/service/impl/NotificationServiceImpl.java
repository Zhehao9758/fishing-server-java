package com.zhehao.fishing.notification.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.enums.NotificationEvent;
import com.zhehao.fishing.enums.NotificationSource;
import com.zhehao.fishing.model.CatchLikeEntity;
import com.zhehao.fishing.model.NotificationEntity;
import com.zhehao.fishing.model.PostCommentEntity;
import com.zhehao.fishing.model.PostLikeEntity;
import com.zhehao.fishing.notification.mapper.NotificationMapper;
import com.zhehao.fishing.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, NotificationEntity> implements NotificationService{
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationServiceImpl(NotificationMapper notificationMapper){
        this.notificationMapper = notificationMapper;
    }


    @Override
    public NotificationEntity buildNotification(CatchLikeEntity catchLikeEntity) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUserId(catchLikeEntity.getUserId());
        notificationEntity.setNotificationEvent(NotificationEvent.LIKE);
        notificationEntity.setNotificationSource(NotificationSource.CATCH);
        notificationEntity.setNotificationTime(catchLikeEntity.getLikeTime());
        notificationEntity.setIsRead(false);
        notificationEntity.setSourceId(catchLikeEntity.getCatchId());
        notificationEntity.setMessagePreview("username liked your catch catch_title");
        return notificationEntity;
    }

    @Override
    public NotificationEntity buildNotification(PostLikeEntity postLikeEntity) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUserId(postLikeEntity.getUserId());
        notificationEntity.setNotificationEvent(NotificationEvent.LIKE);
        notificationEntity.setNotificationSource(NotificationSource.POST);
        notificationEntity.setNotificationTime(postLikeEntity.getLikeTime());
        notificationEntity.setIsRead(false);
        notificationEntity.setSourceId(postLikeEntity.getPostId());
        notificationEntity.setMessagePreview("username liked your post post_title");
        return notificationEntity;
    }

    @Override
    public NotificationEntity buildNotification(PostCommentEntity postCommentEntity) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUserId(postCommentEntity.getUserId());
        notificationEntity.setNotificationEvent(NotificationEvent.COMMENT);
        notificationEntity.setNotificationSource(NotificationSource.POST);
        notificationEntity.setNotificationTime(postCommentEntity.getCommentTime());
        notificationEntity.setIsRead(false);
        notificationEntity.setSourceId(postCommentEntity.getPostId());
        notificationEntity.setMessagePreview("username commented your post post_title");
        return notificationEntity;
    }

    @Override
    public void sendNotification(NotificationEntity notificationEntity) {
        notificationMapper.insertNotification(notificationEntity);
    }

    @Override
    public NotificationEntity getNotificationById(Long id) {
        return notificationMapper.getNotificationById(id);
    }

    @Override
    public void markAsRead(Long id) {
        notificationMapper.markAsRead(id);
    }

    @Override
    public void deleteNotificationById(Long id) {
        notificationMapper.deleteNotificationById(id);
    }

    @Override
    public List<NotificationEntity> getNotificationsByUserIdOrderedByTime(Long userId) {
        return notificationMapper.getNotificationsByUserIdOrderedByTime(userId);
    }
}
