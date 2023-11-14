package com.zhehao.fishing.like.service;

import com.zhehao.fishing.model.CatchEntity;
import com.zhehao.fishing.model.CatchLikeEntity;
import com.zhehao.fishing.model.PostEntity;
import com.zhehao.fishing.model.PostLikeEntity;

import java.util.List;

public interface LikeService {
    boolean likeCatch(CatchLikeEntity catchLikeEntity);
    boolean likePost(PostLikeEntity postLikeEntity);
    boolean unlikeCatch(CatchLikeEntity catchLikeEntity);
    boolean unlikePost(PostLikeEntity postLikeEntity);
    List<CatchEntity> myLikesOfCatch(Long userId);
    List<PostEntity> myLikesOfPost(Long userId);

}
