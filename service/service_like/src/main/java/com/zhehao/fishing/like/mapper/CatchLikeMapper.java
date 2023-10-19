package com.zhehao.fishing.like.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhehao.fishing.model.CatchLikeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CatchLikeMapper extends BaseMapper<CatchLikeEntity> {

    void likeCatch(CatchLikeEntity catchLikeEntity);
    void unlikeCatch(CatchLikeEntity catchLikeEntity);
    List<Long> myLikesOfCatch(Long userId);


}
