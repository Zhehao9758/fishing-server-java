package com.zhehao.fishing.community.service.impl;

import com.zhehao.fishing.common.interfaces.CommentsInPostService;
import com.zhehao.fishing.community.mapper.CommunityMapper;
import com.zhehao.fishing.exceptions.PostNotFoundException;
import com.zhehao.fishing.model.PostEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DubboService(interfaceClass = CommentsInPostService.class)
public class CommentsInPostServiceImpl implements CommentsInPostService {

    private final CommunityMapper communityMapper;


    @Autowired
    public CommentsInPostServiceImpl(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    @Override
    public void incrementComments(Long id) {
        if(communityMapper.getPostById(id)==null){
            throw new PostNotFoundException("Post not exits");
        }
        communityMapper.incrementComments(id);
    }

    @Override
    public void decrementComments(Long id) {
        PostEntity postEntity = communityMapper.getPostById(id);
        if(postEntity==null){
            throw new PostNotFoundException("Post not exits");
        }
        if(postEntity.getCommentsNum() <= 0){
            throw new IllegalStateException("No Comment to delete");
        }
        communityMapper.decrementComments(id);
    }
}
