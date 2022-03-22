package com.codinglife.service.impl;

import com.codinglife.entity.CourseDescriptionDo;
import com.codinglife.entity.CourseDo;
import com.codinglife.entity.vo.CourseInfoVo;
import com.codinglife.exception.CustomizeApiException;
import com.codinglife.exception.GlobalExceptionHandler;
import com.codinglife.mapper.CourseMapper;
import com.codinglife.service.CourseDescriptionService;
import com.codinglife.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-22
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseDo> implements CourseService {

    @Autowired
    CourseDescriptionService courseDescriptionService;

    /**
     * 添加课程信息
     * @param courseInfoVo
     * @return
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加课程信息
        CourseDo courseDo = new CourseDo();
        BeanUtils.copyProperties(courseInfoVo, courseDo);
        int insert = baseMapper.insert(courseDo);
        if (insert <= 0) {
            throw new CustomizeApiException(20001, "添加课程信息失败");
        }
        //获取保存后的id,与课程描述建立关系
        String courseId = courseDo.getId();
        //向课程简介添加课程简介
        CourseDescriptionDo courseDescription = new CourseDescriptionDo();
        BeanUtils.copyProperties(courseInfoVo, courseDescription);
        courseDescription.setId(courseId);
        courseDescriptionService.save(courseDescription);

        return courseId;
    }
}
