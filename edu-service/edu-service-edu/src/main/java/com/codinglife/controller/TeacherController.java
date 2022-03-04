package com.codinglife.controller;


import com.codinglife.CommonResult;
import com.codinglife.entity.Teacher;
import com.codinglife.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author CodingLife
 * @since 2022-03-03
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * @return CommonResult 统一返回结果
     */
    @GetMapping("listAll")
    @ResponseBody
    @ApiOperation(value = "所有讲师列表")
    public CommonResult list(){
        List<Teacher> list = teacherService.list(null);
        return CommonResult.success().data(list);
    }

    /**
     * @param id
     * @return
     * @ApiParam name-参数名称 value-参数简单描述 required-是否为必传参数
     */
    @DeleteMapping("delete/{id}")
    @ResponseBody
    @ApiOperation("通过id删除讲师")
    public CommonResult deleteTeacherById(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean result =  teacherService.removeById(id);
        if (result) {
            return CommonResult.success();
        } else {
            return CommonResult.error();
        }

    }
}

