package com.codinglife.service;

import com.codinglife.entity.CourseDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codinglife.entity.vo.CourseInfoVo;

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
}
