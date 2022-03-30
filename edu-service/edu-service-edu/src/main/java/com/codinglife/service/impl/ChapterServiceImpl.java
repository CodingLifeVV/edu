package com.codinglife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codinglife.entity.ChapterDo;
import com.codinglife.entity.VideoDo;
import com.codinglife.entity.vo.ChapterVo;
import com.codinglife.entity.vo.VideoVo;
import com.codinglife.exception.CustomizeApiException;
import com.codinglife.mapper.ChapterMapper;
import com.codinglife.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codinglife.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-25
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, ChapterDo> implements ChapterService {

    @Autowired
    private VideoService videoService;

    /**
     * 根据课程id获取所有章节信息
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterVoByCourseId(String courseId) {
        // 1.根据课程id查询课程里面所有的章节
        QueryWrapper<ChapterDo> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<ChapterDo> chapterList = baseMapper.selectList(chapterQueryWrapper);

        //2根据课程id查询课程里面所有的小节
        QueryWrapper<VideoDo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<VideoDo> videoList = videoService.list(videoQueryWrapper);

        //创建list集合进行最终封装
        ArrayList<ChapterVo> finalList = new ArrayList<>();
        //3遍历查询章节list集合进行封装
        for (int i = 0; i < chapterList.size(); i++) {
            ChapterDo chapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            //4遍历查询小节list集合进行封装
            ArrayList<VideoVo> finalVideoVoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                VideoDo video = videoList.get(j);
                if (video.getChapterId().equals(chapterVo.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    finalVideoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(finalVideoVoList);
            finalList.add(chapterVo);
        }
        return finalList;
    }

    /**
     * 根据章节id删除章节,如果章节有小节则不能删除
     * @param chapterId
     * @return
     */
    @Override
    public boolean deleteChapterByCharpterId(String chapterId) {
        //根据章节id，如果有小节数据则不能删除
        QueryWrapper<VideoDo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("chapter_id", chapterId);
        Long count = videoService.count(videoWrapper);
        if (count > 0) {
            //有小节
            throw new CustomizeApiException(20001, "章节存在小节,无法删除");
        }
        int i = baseMapper.deleteById(chapterId);
        return i > 0;
    }

    /**
     * 根据课程id删除章节
     * @param courseId
     */
    @Override
    public void deleteChapterByCourseId(String courseId) {
        QueryWrapper<ChapterDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);
    }
}
