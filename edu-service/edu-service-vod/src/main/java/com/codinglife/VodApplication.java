package com.codinglife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Description: 阿里云视频点播服务入口
 * @author: CodingLifeVV
 * @date: 2022.04.10
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableWebMvc
@EnableOpenApi
@EnableDiscoveryClient  //nacos注册
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}
