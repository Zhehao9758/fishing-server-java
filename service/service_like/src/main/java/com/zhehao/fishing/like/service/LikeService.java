package com.zhehao.fishing.like.service;

import com.zhehao.fishing.model.CatchEntity;
import com.zhehao.fishing.model.CatchLikeEntity;
import com.zhehao.fishing.model.PostEntity;
import com.zhehao.fishing.model.PostLikeEntity;

import java.util.List;

public interface LikeService {
    void likeCatch(CatchLikeEntity catchLikeEntity);
    void likePost(PostLikeEntity postLikeEntity);
    void unlikeCatch(CatchLikeEntity catchLikeEntity);
    void unlikePost(PostLikeEntity postLikeEntity);
    List<CatchEntity> myLikesOfCatch(Long userId);
    List<PostEntity> myLikesOfPost(Long userId);

}
