package com.zhehao.fishing.common.interfaces;

import com.zhehao.fishing.model.PostEntity;

public interface LikesInPostService {
    void incrementLikes(Long id);
    void decrementLikes(Long id);
    PostEntity getPostById(Long id);
}
