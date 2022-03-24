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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-22
 */
@Service
@Slf4j
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
        CourseDo course = new CourseDo();
        BeanUtils.copyProperties(courseInfoVo, course);
        int insert = baseMapper.insert(course);
        if (insert <= 0) {
            throw new CustomizeApiException(20001, "添加课程信息失败");
        }
        //获取保存后的id,与课程描述建立关系
        String courseId = course.getId();
        //向课程简介添加课程简介
        CourseDescriptionDo courseDescription = new CourseDescriptionDo();
        BeanUtils.copyProperties(courseInfoVo, courseDescription);
        courseDescription.setId(courseId);
        courseDescriptionService.save(courseDescription);

        return courseId;
    }

    /**
     * 通过id查询课程信息
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfoById(String courseId) {
        //log.info
        //1查询课程表类容
        CourseDo course = baseMapper.selectById(courseId);
        //CourseDo course2 = baseMapper.selectOne(courseId);

        //封装到 CourseInfoVo 中
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        // 2查询描述表
        CourseDescriptionDo courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    /**
     * 更新课程信息
     * @param courseInfoVo
     */
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        CourseDo course = new CourseDo();
        BeanUtils.copyProperties(courseInfoVo, course);
        int updateResult = baseMapper.updateById(course);
        if (updateResult == 0) {
            throw new CustomizeApiException(20001, "修改课程信息失败");
        }

        //2 修改描述表
        CourseDescriptionDo courseDescription = new CourseDescriptionDo();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    /**
     * 根据id删除课程
     * @param courseId
     */
    @Override
    public void removeCourse(String courseId) {

    }
}
