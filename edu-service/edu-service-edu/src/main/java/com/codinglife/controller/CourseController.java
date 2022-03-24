package com.codinglife.controller;


import com.codinglife.CommonResult;
import com.codinglife.entity.vo.CourseInfoVo;
import com.codinglife.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-22
 */
@Api("添加课程信息")
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("添加课程信息")
    @PostMapping("addCourseInfo")
    public CommonResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return CommonResult.success().data("courseId", id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public CommonResult getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfoById(courseId);
        return CommonResult.success().data("courseInfo", courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public CommonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return CommonResult.success();
    }

}

