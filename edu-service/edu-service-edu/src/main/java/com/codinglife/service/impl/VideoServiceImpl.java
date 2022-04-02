package com.codinglife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codinglife.entity.VideoDo;
import com.codinglife.mapper.VideoMapper;
import com.codinglife.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-25
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoDo> implements VideoService {

    /**
     * 根据课程id删除小节内容
     * @param courseId
     */
    @Override
    public void removeByCourseId(String courseId) {
        //根据课程id查出所有视频的id
        QueryWrapper<VideoDo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.select("video_source_id");
        List<VideoDo> videos = baseMapper.selectList(wrapper);
        //封装 video_source_id
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < videos.size(); i++) {
            VideoDo video = videos.get(i);
            String videoSourceId = video.getVideoSourceId();
            if (!videoSourceId.isEmpty()) {
                list.add(videoSourceId);
            }
        }

        // 未完。。。

    }
}
