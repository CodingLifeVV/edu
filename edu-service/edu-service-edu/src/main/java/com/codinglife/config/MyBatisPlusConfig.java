package com.codinglife.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/3 10:13
 */
@Configuration
@MapperScan("com.codinglife.mapper")
public class MyBatisPlusConfig {
}
