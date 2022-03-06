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

import javax.validation.Valid;
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
@ResponseBody
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
    @ApiOperation(value = "所有讲师列表")
    public CommonResult list(){
        List<Teacher> list = teacherService.list(null);

        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new ArithmeticException();
        }

        return CommonResult.success().data(this.LIST_KEY, list);
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "分页查询教师数据")
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
     *
     * @param teacher
     * @return
     * @RequestBody 将请求参数绑定到 request body 中, 封装为具体的 JavaBean
     */
    @ApiOperation("添加教师")
    @PostMapping("addTeacher")
    public CommonResult addTeacher(@Valid @RequestBody Teacher teacher) {
    boolean result = teacherService.save(teacher);
    if (result) {
        return CommonResult.success();
    } else
        return CommonResult.error();
    }

    /**
     *
     * @param id 教师ID
     * @return
     */
    @ApiOperation("根据ID查询教师")
    @GetMapping("getTeacher/{id}")
    public CommonResult getTeacherById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return CommonResult.success().data("teacher", teacher);
    }

    @ApiOperation("修改教师信息")
    @PostMapping("updateTeacher")
    public CommonResult updateTeacher(@RequestBody Teacher teacher) {
        boolean result = teacherService.updateById(teacher);
        if (result) {
            return CommonResult.success();
        } else
            return CommonResult.error();
    }

    /**
     * @param id
     * @return
     * @ApiParam name-参数名称 value-参数简单描述 required-是否为必传参数
     */
    @DeleteMapping("delete/{id}")
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

