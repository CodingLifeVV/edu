package com.codinglife.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 阿里云短信服务常量配置类
 * @author: CodingLifeVV
 * @date: 2022.04.24
 */
@Configuration
public class AliyunMsmConfigConstant implements InitializingBean {

    @Value("${aliyun.msm.file.keyid}")
    private String keyId;
    @Value("${aliyun.msm.file.keysecret}")
    private String keySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }

}
