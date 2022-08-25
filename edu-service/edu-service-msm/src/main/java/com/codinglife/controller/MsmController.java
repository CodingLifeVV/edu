package com.codinglife.controller;

import com.codinglife.CommonResult;
import com.codinglife.RandomUtil;
import com.codinglife.ResultCode;
import com.codinglife.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 短信发送接口
 * @author: CodingLifeVV
 * @date: 2022.04.24
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 发送短信
     * @param phone
     * @return
     */
    @GetMapping("send/{phone}")
    public CommonResult sendMsm(@PathVariable String phone) throws Exception {
        // 从redis中获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!ObjectUtils.isEmpty(code)){
            return CommonResult.success();
        }

        // 如果redis获取不到，进行阿里云发送
        //生成随机数，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code",code);
        // 调用 Service 发送短信服务
        boolean isSend = msmService.send(param,phone);
        if (isSend) {
            //阿里云发送成功，把发送成功的验证码放入redis缓存中
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return CommonResult.success();
        }else
            return CommonResult.setCommonResult(ResultCode.ERROR, "短信发送失败");
    }
}
