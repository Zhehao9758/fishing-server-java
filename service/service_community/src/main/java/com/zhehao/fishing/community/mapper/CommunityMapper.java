package com.zhehao.fishing.community.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhehao.fishing.model.PostEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommunityMapper extends BaseMapper<PostEntity> {

    List<PostEntity> selectByIdList(List<Long> ids);
    void insertPost(PostEntity post);
    PostEntity getPostById(Long post_id);
    List<PostEntity> getPostsByUserIdOrderedByTime(Long user_id);
    void deletePostById(long post_id);
    void updatePost(PostEntity post);
    List<PostEntity> getPostPage(int pageNumber, int size);
    void incrementLikes(long id);
    void incrementComments(long id);
    void decrementLikes(long id);
    void decrementComments(long id);
}
