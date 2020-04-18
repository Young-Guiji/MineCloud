package com.springboot.cloud.resourcemanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.springboot.cloud.common.core","com.springboot.cloud.resourcemanage"})
@ComponentScan(basePackages = {"com.springboot.cloud.common.core","com.springboot.cloud.resourcemanage"})
@MapperScan(value = {"com.springboot.cloud.common.core.message.mapper","com.springboot.cloud.resourcemanage.mapper"})
public class ResourceManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceManageApplication.class, args);
    }

}
