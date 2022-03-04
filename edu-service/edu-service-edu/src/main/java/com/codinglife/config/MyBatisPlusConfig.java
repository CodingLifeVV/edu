package com.codinglife.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
