package com.codinglife.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codinglife.CommonResult;
import com.codinglife.entity.CourseDo;
import com.codinglife.entity.TeacherDo;
import com.codinglife.service.CourseService;
import com.codinglife.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 前端查询课程名师接口
 * @author: CodingLifeVV
 * @date: 2022.04.22
 */
@RestController
@RequestMapping("edu/indexfront")
@CrossOrigin
public class EduIndexController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    /**
     * 查询热门课程和名师
     * @return
     */
    @GetMapping("index")
    public CommonResult index(){
        // 查询前八条热门记录
        QueryWrapper<CourseDo> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<CourseDo> courseList = courseService.list(courseQueryWrapper);

        // 查询前四个名师
        QueryWrapper<TeacherDo> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<TeacherDo> teacherList = teacherService.list(teacherQueryWrapper);

        return CommonResult.success().data("eduList",courseList).data("teacherList",teacherList);
    }


}
