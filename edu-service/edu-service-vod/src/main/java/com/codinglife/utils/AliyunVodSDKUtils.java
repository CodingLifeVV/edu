package com.codinglife.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Description: 阿里云视频点播服务工具类
 * @author: CodingLifeVV
 * @date: 2022.04.10
 */
public class AliyunVodSDKUtils {

    /**
     * 填入AccessKey信息，进行初始化
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws
     */
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
