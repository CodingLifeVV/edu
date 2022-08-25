package com.codinglife.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.codinglife.service.MsmService;
import com.codinglife.utils.SendSmsUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
*@Description: 短信服务实现类
*@author: CodingLifeVV
*@date: 2022.04.24
*/
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) throws Exception {
        return SendSmsUtils.send(param, phone);
    }
}
