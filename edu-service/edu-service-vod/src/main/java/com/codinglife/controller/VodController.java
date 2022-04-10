package com.codinglife.controller;

import com.codinglife.CommonResult;
import com.codinglife.ResultCode;
import com.codinglife.service.VodService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 阿里云视频点播controller接口
 * @author: CodingLifeVV
 * @date: 2022.04.10
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public CommonResult uploadVideo(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return CommonResult.success().data("videoId", videoId);
    }

    /**
     * 删除云端视频
     * @param videoId
     * @return
     */
    @DeleteMapping("deleteVideoSource/{videoId}")
    public CommonResult removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId) {
        vodService.removeVideo(videoId);
        return CommonResult.setCommonResult(ResultCode.SUCCESS, "视频删除成果");
    }
}
