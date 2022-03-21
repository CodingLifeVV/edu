package com.codinglife.controller;

import com.codinglife.CommonResult;
import com.codinglife.entity.subject.OneLevelCourseSubject;
import com.codinglife.service.CourseSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.burningwave.core.assembler.StaticComponentContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/18 11:13
 * https://alibaba-easyexcel.github.io/quickstart/read.html
 */
@RestController
@CrossOrigin
@Api("课程分类Controller")
@RequestMapping("/edu/subject")
public class CourseSubjectController {

    @Autowired
    private CourseSubjectService courseSubjectService;

    /**
     * 添加课程分类
     * @param file
     * @return
     */
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public CommonResult addCourseSubject(@RequestParam("file") MultipartFile file) {
        StaticComponentContainer.Modules.exportAllToAll(); // 解决JDK17与easyexcel报错问题
        courseSubjectService.batchImportSubject(file, courseSubjectService);
        return CommonResult.success();
    }


    /**
     * 返回课程分类结构(树形)
     * @return
     */
    @GetMapping("getAllSubject")
    public CommonResult getAllNestedCourseSubject() {
        List<OneLevelCourseSubject> list = courseSubjectService.getAlltNestedCourseSubject();
        return CommonResult.success().data("courseSubjectList", list);
    }

}
