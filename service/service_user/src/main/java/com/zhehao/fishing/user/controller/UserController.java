package com.zhehao.fishing.user.controller;

import com.zhehao.fishing.model.UserEntity;
import com.zhehao.fishing.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("findAll")
    public List<UserEntity> findAllUser() {
        return userService.list();
    }

    @PostMapping("/insert")
    public String addUser(@RequestBody UserEntity user){
        userService.insertUser(user);
        return "User added successfully";
    }

}
