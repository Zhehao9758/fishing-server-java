package com.zhehao.fishing.user.controller;

import com.zhehao.fishing.exceptions.UserAlreadyExistsException;
import com.zhehao.fishing.model.UserEntity;
import com.zhehao.fishing.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> findAllUser() {
        return userService.list();
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user){
        try {
            userService.insertUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> authentication(@RequestBody Map<String, String> credentials){
        if(userService.authentication(credentials.get("email"), credentials.get("password"))){
            return ResponseEntity.ok("Login success");
        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody UserEntity user){
        userService.updateUser(user);
        return "User updated successfully";
    }

    @DeleteMapping("/delete")
    public String deleteUserByEmail(@RequestBody Map<String, String> credentials){
        userService.deleteUserByEmail(credentials.get("email"));
        return "User deleted successfully";
    }

}
