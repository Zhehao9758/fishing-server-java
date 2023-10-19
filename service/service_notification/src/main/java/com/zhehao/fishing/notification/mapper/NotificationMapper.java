package com.zhehao.fishing.notification.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhehao.fishing.model.CatchEntity;
import com.zhehao.fishing.model.NotificationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface NotificationMapper extends BaseMapper<NotificationEntity> {

    void insertNotification(NotificationEntity notificationEntity);
    NotificationEntity getNotificationById(Long id);
    void markAsRead(Long id);
    void deleteNotificationById(Long id);
    List<NotificationEntity> getNotificationsByUserIdOrderedByTime(Long userId);

}
