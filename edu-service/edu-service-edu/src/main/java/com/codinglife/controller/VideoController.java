package com.codinglife.controller;


import com.codinglife.CommonResult;
import com.codinglife.client.VodClient;
import com.codinglife.entity.TeacherDo;
import com.codinglife.entity.VideoDo;
import com.codinglife.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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

    @Autowired
    private VodClient vodClient;

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

    /**
     * 删除小节,同时删除小节内的视频
     * @param id
     * @return
     */
    @DeleteMapping("deleteVideo/{id}")
    public CommonResult deleteVideo(@PathVariable String id) {
        System.out.println(id);
        //根据小节id查询出视频id，进行删除
        VideoDo video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        //判断是否有视频,有就删除
        if (!ObjectUtils.isEmpty(videoSourceId)) {
            //远程调用vod删除视频
            vodClient.removeVideo(videoSourceId);
        }
        //删除小节
        videoService.removeById(id);
        return CommonResult.success();
    }

    /**
     * 根据id查询小节信息
     * @param videoId
     * @return
     */
    @GetMapping("getVideoInfo/{videoId}")
    public CommonResult getChapterInfo(@PathVariable String videoId) {
        VideoDo video = videoService.getById(videoId);
        return CommonResult.success().data("video", video);
    }

    /**
     * 更新小节内容
     * @param video
     * @return
     */
    @PostMapping("updateVideoInfo")
    public CommonResult updateVideoInfo(@RequestBody VideoDo video) {
        boolean result = videoService.updateById(video);
        if (result) {
            return CommonResult.success();
        } else
            return CommonResult.error();
    }
}

