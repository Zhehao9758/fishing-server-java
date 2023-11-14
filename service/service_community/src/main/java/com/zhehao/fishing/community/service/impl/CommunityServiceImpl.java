package com.zhehao.fishing.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.common.interfaces.*;
import com.zhehao.fishing.community.mapper.CommunityMapper;
import com.zhehao.fishing.community.service.CommunityService;
import com.zhehao.fishing.exceptions.PostNotFoundException;
import com.zhehao.fishing.model.PostEntity;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@DubboService(interfaceClass = PostBatchService.class)
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, PostEntity> implements CommunityService, PostBatchService {
    private final CommunityMapper communityMapper;

    @DubboReference(interfaceClass = PostsInLikeService.class)
    private PostsInLikeService postsInLikeService;

    @DubboReference(interfaceClass = PostsInCommentService.class)
    private PostsInCommentService postsInCommentService;

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
    @GlobalTransactional
    public void deletePostById(long post_id) {
        if(getPostById(post_id) == null){
            throw new PostNotFoundException("Post not exists");
        }
        communityMapper.deletePostById(post_id);
        // delete likes and comments
        postsInLikeService.deleteLikesByPostId(post_id);
        postsInCommentService.deleteCommentsByPostId(post_id);
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
    public List<PostEntity> getPostsByIdList(List<Long> ids) {
        return communityMapper.selectByIdList(ids);
    }

}
