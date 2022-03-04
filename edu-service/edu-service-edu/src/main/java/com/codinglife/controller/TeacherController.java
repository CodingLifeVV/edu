package com.codinglife.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codinglife.CommonResult;
import com.codinglife.entity.Teacher;
import com.codinglife.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
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

    /**
     * 返回数据如果为 List 列表类型, 将数据存储在 key 值为 Map 的集合
     */
    private static String LIST_KEY = "items";

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
        return CommonResult.success().data(this.LIST_KEY, list);
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "分页查询教师数据")
    @ResponseBody
    @GetMapping("listpage/{current}/{limit}")
    public CommonResult listByPage(@PathVariable Long current,
                                   @PathVariable Long limit) {
        // current-当前页 limit-每页显示条数
        Page<Teacher> pageTeacher = new Page<Teacher>(current, limit);
        teacherService.page(pageTeacher);
        Long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();//获取分页后的list集合

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return CommonResult.success().data(map);
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

