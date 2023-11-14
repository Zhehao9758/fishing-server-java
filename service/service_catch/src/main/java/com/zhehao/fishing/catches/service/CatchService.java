package com.zhehao.fishing.catches.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhehao.fishing.model.CatchEntity;
import java.util.List;

public interface CatchService extends IService<CatchEntity> {
    void insertCatch(CatchEntity catchEntity);
    CatchEntity getCatchById(Long id);
    List<CatchEntity> getCatchesByUserIdOrderedByTime(Long user_id);
    void updateCatch(CatchEntity catchEntity);
    void deleteCatchById(Long id);

    List<CatchEntity> getCatchPage(int pageNumber, int size);

    String generateTemporaryUploadUrl(Long userId);


}
