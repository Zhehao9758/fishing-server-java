package com.zhehao.fishing.fish;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceFishApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceFishApplication.class, args);
    }
}