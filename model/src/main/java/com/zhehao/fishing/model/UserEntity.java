package com.zhehao.fishing.model;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("users")
public class UserEntity{
    private Long user_id;
    private String username;
    private String password; // encrypted

    private String email;

    public UserEntity(){

    }

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = encryptPassword(password); // 加密密码
        this.email = email;
    }

    public Long getUser_Id() {
        return user_id;
    }

    public void setUser_Id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    private String encryptPassword(String password) {
        // 使用 BCryptPasswordEncoder 进行密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
