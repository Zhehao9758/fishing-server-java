package com.zhehao.fishing.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhehao.fishing.model.PostEntity;

import java.util.List;

public interface CommunityService extends IService<PostEntity> {
    void insertPost(PostEntity post);
    PostEntity getPostById(Long post_id);
    List<PostEntity> getPostsByUserIdOrderedByTime(Long user_id);
    void deletePostById(long post_id);
    void updatePost(PostEntity post);
    List<PostEntity> getPostPage(int pageNumber, int size);
    void incrementLikes(long id);
    void incrementComments(long id);
    void decrementLikes(long id);
    void decrementComments(long id);
}
