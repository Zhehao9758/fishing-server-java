package com.zhehao.fishing.community.service.impl;

import com.zhehao.fishing.common.interfaces.LikesInPostService;
import com.zhehao.fishing.community.mapper.CommunityMapper;
import com.zhehao.fishing.exceptions.PostNotFoundException;
import com.zhehao.fishing.model.PostEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DubboService(interfaceClass = LikesInPostService.class)
public class LikesInPostServiceImpl implements LikesInPostService {
    private final CommunityMapper communityMapper;


    @Autowired
    public LikesInPostServiceImpl(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    @Override
    public void incrementLikes(Long id) {
        if(communityMapper.getPostById(id)==null){
            throw new PostNotFoundException("Post not exits");
        }
        communityMapper.incrementLikes(id);
    }

    @Override
    public void decrementLikes(Long id) {
        PostEntity postEntity = communityMapper.getPostById(id);
        if(postEntity==null){
            throw new PostNotFoundException("Post not exits");
        }
        if(postEntity.getLikesNum() <= 0){
            throw new IllegalStateException("No like to cancel");
        }
        communityMapper.decrementLikes(id);
    }
}
