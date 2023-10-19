package com.zhehao.fishing.common.interfaces;

public interface CommentsInPostService {

    void incrementComments(Long id);
    void decrementComments(Long id);
}
