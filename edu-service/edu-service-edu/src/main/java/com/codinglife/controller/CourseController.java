package com.codinglife.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codinglife.CommonResult;
import com.codinglife.entity.CourseDo;
import com.codinglife.entity.vo.CourseInfoVo;
import com.codinglife.entity.vo.CoursePublishVo;
import com.codinglife.entity.vo.CourseQueryVo;
import com.codinglife.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    /**
     * 添加课程信息
     * @param courseInfoVo
     * @return
     */
    @ApiOperation("添加课程信息")
    @PostMapping("addCourseInfo")
    public CommonResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return CommonResult.success().data("courseId", id);
    }

    /**
     * 根据课程id查询课程基本信息
     * @param courseId
     * @return
     */
    @GetMapping("getCourseInfo/{courseId}")
    public CommonResult getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfoById(courseId);
        return CommonResult.success().data("courseInfo", courseInfoVo);
    }

    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("updateCourseInfo")
    public CommonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return CommonResult.success();
    }

    /**
     * 根据课程id查询待发布课程信息
     * @param courseId
     * @return
     */
    @GetMapping("getPublishCourseInfo/{courseId}")
    public CommonResult getPublishCourseInfo(@PathVariable String courseId) {
        CoursePublishVo coursePublishVo = courseService.getPublishCourseInfo(courseId);
        return CommonResult.success().data("publishCourse", coursePublishVo);
    }

    /**
     * 发布课程
     * @param courseId
     * @return
     */
    @PostMapping("publishCourse/{courseId}")
    public CommonResult publishCourse(@PathVariable String courseId) {
        CourseDo course = new CourseDo();
        course.setId(courseId);
        course.setStatus("Normal");
        courseService.updateById(course);
        return CommonResult.success();
    }

    /**
     * 条件分页查询课程信息
     * @param current 当前也
     * @param limit 每页限制显示行数
     * @param courseQuery 封装查询结果
     * @return
     */
    @ApiOperation(value = "条件查询分页方法")
    @PostMapping("listByPageCondition/{current}/{limit}")
    public CommonResult listByPageCondition(@PathVariable Long current,
                                 @PathVariable Long limit,
                                 @RequestBody(required = false) CourseQueryVo courseQuery) {
        //创建page
        Page<CourseDo> pageCondition = new Page<>(current, limit);

        //QueryWrapper,构建
        QueryWrapper<CourseDo> wrapper = new QueryWrapper<>();
        //多条件组合查询，动态sql
        String status = courseQuery.getStatus();
        String title = courseQuery.getTitle();
        if (!ObjectUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!ObjectUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }

        wrapper.orderByDesc("gmt_create");

        //调用方法，实现分页查询
        courseService.page(pageCondition, wrapper);

        long total = pageCondition.getTotal();//获取总记录数
        List<CourseDo> records = pageCondition.getRecords();//获取分页后的list集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);
        return CommonResult.success().data(map);
    }

    /**
     * 根据课程id删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("deleteCourse/{courseId}")
    public  CommonResult removeCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return CommonResult.success();
    }

}

