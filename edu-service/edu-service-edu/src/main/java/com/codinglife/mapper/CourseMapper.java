package com.codinglife.mapper;

import com.codinglife.entity.CourseDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codinglife.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-22
 */
public interface CourseMapper extends BaseMapper<CourseDo> {
    CoursePublishVo getPublishCourse(String courseId);
}
