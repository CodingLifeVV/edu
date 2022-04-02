package com.codinglife.controller;


import com.codinglife.CommonResult;
import com.codinglife.entity.VideoDo;
import com.codinglife.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-25
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 添加小节内容
     * @param video
     * @return
     */
    @PostMapping("addVideo")
    public CommonResult addVideo(@RequestBody VideoDo video) {
        videoService.save(video);
        return CommonResult.success();
    }

    // 删除小节,同时删除小节内的视频

}

