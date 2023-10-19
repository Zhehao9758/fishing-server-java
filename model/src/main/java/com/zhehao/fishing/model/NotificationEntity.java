package com.zhehao.fishing.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhehao.fishing.enums.NotificationEvent;
import com.zhehao.fishing.enums.NotificationSource;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("notifications")
public class NotificationEntity {

    @TableId(value = "notification_id", type = IdType.AUTO)
    private Long notificationId;

    private Long userId;

    private Timestamp notificationTime;

    private Boolean isRead;

    private String messagePreview;

    private NotificationEvent notificationEvent;

    private NotificationSource notificationSource;

    private Long sourceId;
}
