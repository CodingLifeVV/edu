package com.codinglife.controller;

import com.codinglife.CommonResult;
import com.codinglife.service.CourseSubjectService;
import io.swagger.annotations.Api;
import org.burningwave.core.assembler.StaticComponentContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/18 11:13
 */
@RestController
@CrossOrigin
@Api("课程分类Controller")
@RequestMapping("/edu/subject")
public class CourseSubjectController {

    @Autowired
    private CourseSubjectService courseSubjectService;

    @PostMapping("addSubject")
    public CommonResult addCourseSubject(@RequestParam("file") MultipartFile file) {
        StaticComponentContainer.Modules.exportAllToAll();
        courseSubjectService.batchImportSubject(file, courseSubjectService);
        return CommonResult.success();
    }
}
