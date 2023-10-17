package com.zhehao.fishing.catches.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhehao.fishing.model.CatchEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CatchMapper extends BaseMapper<CatchEntity> {
    void insertCatch(CatchEntity catchEntity);
    CatchEntity getCatchById(long id);
    List<CatchEntity> getCatchesByUserIdOrderedByTime(long user_id);
    void updateCatch(CatchEntity catchEntity);
    void deleteCatchById(long id);

    void incrementLikes(long id);
    void decrementLikes(long id);
}
