package com.codinglife.service;

import com.codinglife.entity.CourseDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codinglife.entity.vo.CourseInfoVo;
import com.codinglife.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-22
 */
public interface CourseService extends IService<CourseDo> {
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfoById(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    void removeCourse(String courseId);

    CoursePublishVo getPublishCourseInfo(String courseId);
}
