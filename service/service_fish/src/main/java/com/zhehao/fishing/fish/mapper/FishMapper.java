package com.zhehao.fishing.fish.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhehao.fishing.model.FishEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FishMapper extends BaseMapper<FishEntity> {
    void insertFish(FishEntity fish);
    FishEntity getFishById(Long fish_id);
    FishEntity getFishByName(String fish_name);
    void updateFish(FishEntity fish);
    void deleteFishById(long fish_id);

}
