package com.zhehao.fishing.catches.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.zhehao.fishing.catches.mapper")
public class CatchConfig {
}
