package com.zhehao.fishing.like.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhehao.fishing.model.CatchEntity;
import com.zhehao.fishing.model.CatchLikeEntity;
import com.zhehao.fishing.model.PostEntity;
import com.zhehao.fishing.model.PostLikeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostLikeMapper extends BaseMapper<PostLikeEntity> {

    void likePost(PostLikeEntity postLikeEntity);
    void unlikePost(PostLikeEntity postLikeEntity);
    List<Long> myLikesOfPost(Long userId);

}
