package com.zhehao.fishing.common.interfaces;

public interface LikesInPostService {
    void incrementLikes(Long id);
    void decrementLikes(Long id);
}
