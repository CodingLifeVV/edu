package com.codinglife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/14 15:34
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // exclude 取消数据源自动配置
@EnableWebMvc
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
