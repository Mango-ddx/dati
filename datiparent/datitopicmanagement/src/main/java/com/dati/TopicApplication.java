package com.dati;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@MapperScan(basePackages = "com.dati.dao")
@SpringBootApplication
public class TopicApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicApplication.class, args);
    }
}
