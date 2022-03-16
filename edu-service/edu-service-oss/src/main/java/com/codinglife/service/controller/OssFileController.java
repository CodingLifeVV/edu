package com.codinglife.service.controller;

import com.codinglife.CommonResult;
import com.codinglife.ResultCode;
import com.codinglife.service.OssFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CodingLife
 * @Description c
 * @since 2022/3/16 10:01
 */
@Api("阿里云OSS文件管理")
@RestController
@RequestMapping("/oss/file")
@CrossOrigin // 解决跨域问题
public class OssFileController {

    @Autowired
    private OssFileService fileService;

    @PostMapping("upload")
    @ApiOperation("上传文件")
    public CommonResult upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String uploadUrl = fileService.upload(file);
        return CommonResult.
                setCommonResult(ResultCode.SUCCESS, "文件上传成功").
                data("url", uploadUrl);
    }

}
