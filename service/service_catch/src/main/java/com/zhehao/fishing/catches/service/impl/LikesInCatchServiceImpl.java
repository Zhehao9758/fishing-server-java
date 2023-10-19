package com.zhehao.fishing.catches.service.impl;

import com.zhehao.fishing.catches.mapper.CatchMapper;
import com.zhehao.fishing.common.interfaces.LikesInCatchService;
import com.zhehao.fishing.exceptions.CatchNotFoundException;
import com.zhehao.fishing.model.CatchEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DubboService(interfaceClass = LikesInCatchService.class)
public class LikesInCatchServiceImpl implements LikesInCatchService {

    private final CatchMapper catchMapper;

    @Autowired
    public LikesInCatchServiceImpl(CatchMapper catchMapper) {
        this.catchMapper = catchMapper;
    }

    @Override
    public void incrementLikes(long id) {
        if(catchMapper.getCatchById(id)==null){
            throw new CatchNotFoundException("Catch not exits");
        }
        catchMapper.incrementLikes(id);
    }

    @Override
    public void decrementLikes(long id){
        CatchEntity catchEntity = catchMapper.getCatchById(id);
        if(catchEntity==null){
            throw new CatchNotFoundException("Catch not exits");
        }
        if(catchEntity.getLikes() <= 0){
            throw new IllegalStateException("No like to cancel");
        }
        catchMapper.decrementLikes(id);
    }
}
