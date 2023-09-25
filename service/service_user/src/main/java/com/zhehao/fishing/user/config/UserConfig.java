package com.zhehao.fishing.user.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.zhehao.fishing.user.mapper")
public class UserConfig {
}
