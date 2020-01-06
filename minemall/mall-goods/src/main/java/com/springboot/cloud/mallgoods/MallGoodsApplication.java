package com.springboot.cloud.mallgoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.springboot.cloud.common.core","com.springboot.cloud.mallgoods"})
@ComponentScan(basePackages = {"com.springboot.cloud.common","com.springboot.cloud.mallgoods"})
@MapperScan(value = {"com.springboot.cloud.common.core.message.mapper","com.springboot.cloud.mallgoods.mapper"})
public class MallGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGoodsApplication.class, args);
    }

}
