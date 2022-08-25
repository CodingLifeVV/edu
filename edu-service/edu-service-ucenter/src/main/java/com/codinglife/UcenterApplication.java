package com.codinglife;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description: 用户中心，登陆注册模块启动类
 * @author: CodingLifeVV
 * @date: 2022.04.25
 */
@SpringBootApplication
@MapperScan("com.codinglife")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
