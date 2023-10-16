package com.zhehao.fishing.skill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceSkillApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSkillApplication.class, args);
    }
}
