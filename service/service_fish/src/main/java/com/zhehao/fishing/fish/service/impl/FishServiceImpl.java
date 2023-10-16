package com.zhehao.fishing.fish.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zhehao.fishing.fish.mapper.FishMapper;
import com.zhehao.fishing.fish.service.FishService;
import com.zhehao.fishing.model.FishEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FishServiceImpl extends ServiceImpl<FishMapper, FishEntity> implements FishService {
    private final FishMapper fishMapper;

    @Autowired
    public FishServiceImpl(FishMapper fishMapper){
        this.fishMapper = fishMapper;
    }


    @Override
    public FishEntity getFishByName(String fish_name) {
        return fishMapper.getFishByName(fish_name);
    }

    @Override
    public FishEntity getFishById(Long fish_id) {
        return fishMapper.getFishById(fish_id);
    }

    @Override
    public List<FishEntity> getFishPage(int pageNumber, int size) {
        Page<FishEntity> page = new Page<>(pageNumber, size);
        QueryWrapper<FishEntity> wrapper = new QueryWrapper<>();
        wrapper.last("LIMIT " + (pageNumber - 1) * size + ", " + size);
        Page<FishEntity> fishEntityPage = fishMapper.selectPage(page, wrapper);
        return fishEntityPage.getRecords();
    }
}
