package com.zhehao.fishing.like;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceLikeApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ServiceLikeApplication.class, args);
    }
}
