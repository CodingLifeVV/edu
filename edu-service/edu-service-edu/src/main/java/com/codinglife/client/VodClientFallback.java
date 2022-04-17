package com.codinglife.client;

import com.codinglife.CommonResult;
import com.codinglife.ResultCode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: Vod服务熔断器降级类
 * @author: CodingLifeVV
 * @date: 2022.04.17
 */
@Component
public class VodClientFallback implements VodClient {

    @Override
    public CommonResult removeVideo(String videoId) {
        return CommonResult.setCommonResult(ResultCode.ERROR,"删除视频出错了");
    }

    @Override
    public CommonResult removeVideoBatch(List<String> videoIdList) {
        return CommonResult.setCommonResult(ResultCode.ERROR,"批量删除视频出错了");
    }
}
