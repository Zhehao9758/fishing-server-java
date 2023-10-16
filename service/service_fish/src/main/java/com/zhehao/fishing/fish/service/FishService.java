package com.zhehao.fishing.fish.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhehao.fishing.model.FishEntity;
import java.util.List;

public interface FishService extends IService<FishEntity> {
    FishEntity getFishByName(String fish_name);
    FishEntity getFishById(Long fish_id);
    List<FishEntity> getFishPage(int pageNum, int size);
}
