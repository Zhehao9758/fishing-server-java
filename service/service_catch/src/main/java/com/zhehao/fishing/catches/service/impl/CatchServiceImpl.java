package com.zhehao.fishing.catches.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.catches.mapper.CatchMapper;
import com.zhehao.fishing.catches.service.CatchService;
import com.zhehao.fishing.exceptions.CatchNotFoundException;
import com.zhehao.fishing.model.CatchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatchServiceImpl extends ServiceImpl<CatchMapper, CatchEntity> implements CatchService {
    private final CatchMapper catchMapper;

    @Autowired
    public CatchServiceImpl(CatchMapper catchMapper){
        this.catchMapper = catchMapper;
    }


    @Override
    public void insertCatch(CatchEntity catchEntity) {
        catchMapper.insertCatch(catchEntity);
    }

    @Override
    public CatchEntity getCatchById(long id) {
        return catchMapper.getCatchById(id);
    }

    @Override
    public List<CatchEntity> getCatchesByUserIdOrderedByTime(long user_id) {
        return catchMapper.getCatchesByUserIdOrderedByTime(user_id);
    }

    @Override
    public void updateCatch(CatchEntity catchEntity) {
        catchMapper.updateCatch(catchEntity);
    }

    @Override
    public void deleteCatchById(long id) {
        if(getCatchById(id)==null){
            throw new CatchNotFoundException("Catch not exits");
        }
        catchMapper.deleteCatchById(id);
    }

    @Override
    public List<CatchEntity> getCatchPage(int pageNumber, int size) {
        Page<CatchEntity> page = new Page<>(pageNumber, size);
        QueryWrapper<CatchEntity> wrapper = new QueryWrapper<>();
        wrapper.last("LIMIT " + (pageNumber - 1) * size + ", " + size);
        Page<CatchEntity> catchEntityPage = catchMapper.selectPage(page, wrapper);
        return catchEntityPage.getRecords();
    }

    @Override
    public void incrementLikes(long id) {
        catchMapper.incrementLikes(id);
    }
}
