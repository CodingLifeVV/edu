package com.codinglife.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyuncs.CommonResponse;

import java.util.Map;

/**
 * @Description: 发送短信工具类
 * @author: CodingLifeVV
 * @date: 2022.04.24
 */
public class SendSmsUtils {
    public static boolean send(Map<String, Object> param, String phone) throws Exception {
        Client client = SendSmsUtils.createClient(AliyunMsmConfigConstant.ACCESS_KEY_ID,
                AliyunMsmConfigConstant.ACCESS_KEY_SECRET);

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")      // 短信签名名称
                .setTemplateCode("SMS_154950909") // 短信模版
                .setPhoneNumbers(phone)           // 接收短信的手机号
                .setTemplateParam(JSONObject.toJSONString(param)); // 短信模版对应的变量值
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse response = client.sendSms(sendSmsRequest);

        if (response.getBody().getCode().equals("OK")) {
            return true;
        }
        return false;
    }

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     * @throws Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
}
