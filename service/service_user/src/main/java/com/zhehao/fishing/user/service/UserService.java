package com.zhehao.fishing.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhehao.fishing.model.UserEntity;

public interface UserService extends IService<UserEntity> {
    void insertUser(UserEntity user);
}
