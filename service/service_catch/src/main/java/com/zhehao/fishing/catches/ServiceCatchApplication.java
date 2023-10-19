package com.zhehao.fishing.catches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class ServiceCatchApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ServiceCatchApplication.class, args);
    }
}
