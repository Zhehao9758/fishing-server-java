package com.zhehao.fishing.like.service.impl;

import com.zhehao.fishing.common.interfaces.*;
import com.zhehao.fishing.like.mapper.CatchLikeMapper;
import com.zhehao.fishing.like.mapper.PostLikeMapper;
import com.zhehao.fishing.like.service.LikeService;
import com.zhehao.fishing.model.CatchEntity;
import com.zhehao.fishing.model.CatchLikeEntity;
import com.zhehao.fishing.model.PostEntity;
import com.zhehao.fishing.model.PostLikeEntity;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService, CatchesInLikeService, PostsInLikeService {
    private final CatchLikeMapper catchLikeMapper;
    private final PostLikeMapper postLikeMapper;
    private final AmqpTemplate amqpTemplate;


    @DubboReference(interfaceClass = LikesInCatchService.class)
    private LikesInCatchService likesInCatchService;

    @DubboReference(interfaceClass = LikesInPostService.class)
    private LikesInPostService likesInPostService;

    @DubboReference(interfaceClass = CatchBatchService.class)
    private CatchBatchService catchBatchService;

    @DubboReference(interfaceClass = PostBatchService.class)
    private PostBatchService postBatchService;

    @Autowired
    public LikeServiceImpl(CatchLikeMapper catchLikeMapper, PostLikeMapper postLikeMapper, AmqpTemplate amqpTemplate){
        this.catchLikeMapper = catchLikeMapper;
        this.postLikeMapper = postLikeMapper;
        this.amqpTemplate = amqpTemplate;
    }


    @GlobalTransactional
    @Override
    public boolean likeCatch(CatchLikeEntity catchLikeEntity) {

        CatchEntity catchEntity = likesInCatchService.getCatchById(catchLikeEntity.getCatchId());
        if(catchEntity == null) return false;
        catchLikeMapper.likeCatch(catchLikeEntity);
        //rpc
        //likes increment
        likesInCatchService.incrementLikes(catchLikeEntity.getCatchId());
        //mq to write notification table
        amqpTemplate.convertAndSend("catch.like.queue", catchLikeEntity);
        return true;

    }

    @Override
    @GlobalTransactional
    public boolean likePost(PostLikeEntity postLikeEntity) {
        PostEntity postEntity = likesInPostService.getPostById(postLikeEntity.getPostId());
        if(postEntity == null) return false;
        postLikeMapper.likePost(postLikeEntity);
        //rpc
        //likes increment
        likesInPostService.incrementLikes(postLikeEntity.getPostId());

        //mq to write notification table
        amqpTemplate.convertAndSend("post.like.queue", postLikeEntity);
        return true;
    }

    //@GlobalTransactional
    @Override
    @GlobalTransactional
    public boolean unlikeCatch(CatchLikeEntity catchLikeEntity) {
        CatchEntity catchEntity = likesInCatchService.getCatchById(catchLikeEntity.getCatchId());
        if(catchEntity == null) return false;
        catchLikeMapper.unlikeCatch(catchLikeEntity);
        //rpc
        //likes decrement
        likesInCatchService.decrementLikes(catchLikeEntity.getCatchId());
        return true;
    }

    //@GlobalTransactional
    @Override
    public boolean unlikePost(PostLikeEntity postLikeEntity) {
        PostEntity postEntity = likesInPostService.getPostById(postLikeEntity.getPostId());
        if(postEntity == null) return false;
        postLikeMapper.unlikePost(postLikeEntity);
        likesInPostService.decrementLikes(postLikeEntity.getPostId());
        return true;
    }

    @Override
    public List<CatchEntity> myLikesOfCatch(Long userId) {
        List<Long> ids = catchLikeMapper.myLikesOfCatch(userId);
        return catchBatchService.getCatchesByIds(ids);
    }

    @Override
    public List<PostEntity> myLikesOfPost(Long userId) {
        List<Long> ids = postLikeMapper.myLikesOfPost(userId);
        return postBatchService.getPostsByIdList(ids);
    }

    @Override
    public void deleteLikesByCatchId(Long catchId) {
        catchLikeMapper.deleteByCatchId(catchId);
    }

    @Override
    public void deleteLikesByPostId(Long postId) {
        postLikeMapper.deleteByPostId(postId);
    }
}
