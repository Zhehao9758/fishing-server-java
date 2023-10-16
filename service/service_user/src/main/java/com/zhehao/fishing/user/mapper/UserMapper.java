package com.zhehao.fishing.user.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhehao.fishing.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<UserEntity>{
    void insertUser(UserEntity user);
    UserEntity getUserByEmail(String email);
    void updateUser(UserEntity user);
    void deleteUserByEmail(String email);
}
