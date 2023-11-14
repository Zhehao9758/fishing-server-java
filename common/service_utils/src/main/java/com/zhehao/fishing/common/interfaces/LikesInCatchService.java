package com.zhehao.fishing.common.interfaces;

import com.zhehao.fishing.model.CatchEntity;

public interface LikesInCatchService {
    void incrementLikes(Long id);
    void decrementLikes(Long id);
    CatchEntity getCatchById(Long catchId);
}
