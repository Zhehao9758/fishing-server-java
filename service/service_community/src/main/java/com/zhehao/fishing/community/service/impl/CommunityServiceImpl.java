package com.zhehao.fishing.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.community.mapper.CommunityMapper;
import com.zhehao.fishing.community.service.CommunityService;
import com.zhehao.fishing.exceptions.PostNotFoundException;
import com.zhehao.fishing.model.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, PostEntity> implements CommunityService {
    private final CommunityMapper communityMapper;

    @Autowired
    public CommunityServiceImpl(CommunityMapper communityMapper){
        this.communityMapper = communityMapper;
    }


    @Override
    public void insertPost(PostEntity post) {
        communityMapper.insertPost(post);
    }

    @Override
    public PostEntity getPostById(Long post_id) {
        return communityMapper.getPostById(post_id);
    }

    @Override
    public List<PostEntity> getPostsByUserIdOrderedByTime(Long user_id) {
        return communityMapper.getPostsByUserIdOrderedByTime(user_id);
    }

    @Override
    public void deletePostById(long post_id) {
        if(getPostById(post_id) == null){
            throw new PostNotFoundException("Post not exists");
        }
        communityMapper.deletePostById(post_id);
    }

    @Override
    public void updatePost(PostEntity post) {
        communityMapper.updatePost(post);
    }

    @Override
    public List<PostEntity> getPostPage(int pageNumber, int size) {
        Page<PostEntity> page = new Page<>(pageNumber, size);
        QueryWrapper<PostEntity> wrapper = new QueryWrapper<>();
        wrapper.last("LIMIT " + (pageNumber - 1) * size + ", " + size);
        Page<PostEntity> postEntityPage = communityMapper.selectPage(page, wrapper);
        return postEntityPage.getRecords();
    }

    @Override
    public void incrementLikes(long id) {
        if(getPostById(id)==null){
            throw new PostNotFoundException("Post not exits");
        }
        communityMapper.incrementLikes(id);
    }

    @Override
    public void incrementComments(long id) {
        if(getPostById(id)==null){
            throw new PostNotFoundException("Post not exits");
        }
        communityMapper.incrementComments(id);
    }

    @Override
    public void decrementLikes(long id) {
        PostEntity postEntity = getPostById(id);
        if(postEntity==null){
            throw new PostNotFoundException("Post not exits");
        }
        if(postEntity.getLikesNum() <= 0){
            throw new IllegalStateException("No like to cancel");
        }
        communityMapper.decrementLikes(id);
    }

    @Override
    public void decrementComments(long id) {
        PostEntity postEntity = getPostById(id);
        if(postEntity==null){
            throw new PostNotFoundException("Post not exits");
        }
        if(postEntity.getCommentsNum() <= 0){
            throw new IllegalStateException("No Comment to delete");
        }
        communityMapper.decrementComments(id);
    }
}