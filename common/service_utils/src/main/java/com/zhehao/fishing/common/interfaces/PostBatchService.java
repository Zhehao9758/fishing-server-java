package com.zhehao.fishing.common.interfaces;

import com.zhehao.fishing.model.PostEntity;

import java.util.List;

public interface PostBatchService {
    List<PostEntity> getPostsByIdList(List<Long> ids);
}
