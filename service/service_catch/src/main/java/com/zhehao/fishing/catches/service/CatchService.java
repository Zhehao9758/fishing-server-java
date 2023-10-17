package com.zhehao.fishing.catches.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhehao.fishing.model.CatchEntity;
import com.zhehao.fishing.model.SkillEntity;

import java.util.List;

public interface CatchService extends IService<CatchEntity> {
    void insertCatch(CatchEntity catchEntity);
    CatchEntity getCatchById(long id);
    List<CatchEntity> getCatchesByUserIdOrderedByTime(long user_id);
    void updateCatch(CatchEntity catchEntity);
    void deleteCatchById(long id);

    List<CatchEntity> getCatchPage(int pageNumber, int size);

    void incrementLikes(long id);
    void decrementLikes(long id);
}
