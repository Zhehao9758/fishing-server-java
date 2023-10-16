package com.zhehao.fishing.fish.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.zhehao.fishing.fish.mapper")
public class FishConfig {
}
