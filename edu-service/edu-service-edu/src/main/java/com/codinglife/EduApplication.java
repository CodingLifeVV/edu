package com.codinglife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/3 10:12
 */
@SpringBootApplication
@EnableWebMvc  // Swagger 开启注解 学习参考 https://segmentfault.com/a/1190000039413795
@EnableOpenApi // Swagger 开启注解 访问 http://localhost:8001/swagger-ui/
@EnableDiscoveryClient  //服务注册
@EnableFeignClients // 服务调用
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
