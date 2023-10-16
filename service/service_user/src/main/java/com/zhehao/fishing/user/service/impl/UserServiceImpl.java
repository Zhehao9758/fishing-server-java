package com.zhehao.fishing.user.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.exceptions.UserAlreadyExistsException;
import com.zhehao.fishing.model.UserEntity;
import com.zhehao.fishing.user.mapper.UserMapper;
import com.zhehao.fishing.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }



    public void insertUser(UserEntity user){
        if(userMapper.getUserByEmail(user.getEmail()) == null){
            userMapper.insertUser(user);
        }else throw new UserAlreadyExistsException("User with this email already exists");
    }

    @Override
    public boolean authentication(String email, String password) {
        UserEntity target = userMapper.getUserByEmail(email);
        if(target == null) return false;
        return passwordEncoder.matches(password, target.getPassword());
    }

    @Override
    public void updateUser(UserEntity user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userMapper.deleteUserByEmail(email);
    }
}
