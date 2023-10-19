package com.zhehao.fishing.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.common.interfaces.CommentsInPostService;
import com.zhehao.fishing.common.interfaces.LikesInCatchService;
import com.zhehao.fishing.common.interfaces.LikesInPostService;
import com.zhehao.fishing.common.interfaces.PostBatchService;
import com.zhehao.fishing.community.mapper.CommunityMapper;
import com.zhehao.fishing.community.service.CommunityService;
import com.zhehao.fishing.exceptions.PostNotFoundException;
import com.zhehao.fishing.model.PostEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@DubboService(interfaceClass = PostBatchService.class)
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, PostEntity> implements CommunityService, PostBatchService {
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
    public List<PostEntity> getPostsByIdList(List<Long> ids) {
        return communityMapper.selectByIdList(ids);
    }

}
