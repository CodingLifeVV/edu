package com.codinglife.client;

import com.codinglife.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * 服务调用客户端
 * @FeignClient 定义从哪里调用服务，"edu-service-vod" 为服务名称，即应用名称，定义在配置文件中
 * @author CodingLife
 * @since 2022-04-12
 */
@FeignClient("edu-service-vod")
@Component
public interface VodClient {
    @DeleteMapping(value = "/eduvod/video/deleteVideoSource/{videoId}")
    public CommonResult removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/eduvod/video/deleteBatch")
    public CommonResult removeVideoBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
