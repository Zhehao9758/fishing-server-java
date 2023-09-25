package com.zhehao.fishing.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.model.UserEntity;
import com.zhehao.fishing.user.mapper.UserMapper;
import com.zhehao.fishing.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public void insertUser(UserEntity user){
        userMapper.insertUser(user);
    }
}
