package com.codinglife.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CodingLife
 * @Description 读取配置文件属性值
 * @since 2022/3/14 15:41
 */
@Configuration
public class AliyunOSSConfigConstant implements InitializingBean {
    // 读取配置文件
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String keyid;
    @Value("${aliyun.oss.accessKeySecret}")
    private String keysecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketname;

    public static String END_POINT; // OSS对外服务的访问域名
    // OSS通过使用AccessKeyId和AccessKeySecret对称加密的方法来验证某个请求的发送者身份
    public static String ACCESS_KEYID; //AccessKey ID
    public static String ACCESS_KEYSECRET; //Access Key Secret
    public static String BUCKET_NAME; // 存储空间, 用于存储对象(Object)的容器

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEYID = keyid;
        ACCESS_KEYSECRET = keysecret;
        BUCKET_NAME = bucketname;
    }


}
