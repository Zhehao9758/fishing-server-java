package com.zhehao.fishing.notification.listener;

import com.zhehao.fishing.model.CatchLikeEntity;
import com.zhehao.fishing.model.PostCommentEntity;
import com.zhehao.fishing.model.PostLikeEntity;
import com.zhehao.fishing.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

    private final NotificationService notificationService;

    public SpringRabbitListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }



    @RabbitListener(queues = "catch.like.queue")
    public void listenCatchLikeQueue(CatchLikeEntity catchLikeEntity) {
        System.out.println(catchLikeEntity);
        //write to db
        notificationService.sendNotification(notificationService.buildNotification(catchLikeEntity));
    }

    @RabbitListener(queues = "post.like.queue")
    public void listenPostLikeQueue(PostLikeEntity postLikeEntity) {
        System.out.println(postLikeEntity);
        //write to db
        notificationService.sendNotification(notificationService.buildNotification(postLikeEntity));
    }

    @RabbitListener(queues = "post.comment.queue")
    public void listenPostCommentQueue(PostCommentEntity postCommentEntity) {
        System.out.println(postCommentEntity);
        //write to db
        notificationService.sendNotification(notificationService.buildNotification(postCommentEntity));
    }


}


