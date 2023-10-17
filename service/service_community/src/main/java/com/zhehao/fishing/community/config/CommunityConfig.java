package com.zhehao.fishing.community.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.zhehao.fishing.community.mapper")
public class CommunityConfig {
}
