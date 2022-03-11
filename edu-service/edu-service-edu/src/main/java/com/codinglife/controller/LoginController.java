package com.codinglife.controller;

import com.codinglife.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/11 19:43
 */

@Api("用户登录")
@RequestMapping("/edu/user")
@RestController // @Controller + @ResponseBody
@CrossOrigin // 解决跨域问题
public class LoginController {

    @ApiOperation("用户登录")
    @PostMapping("login")
    public CommonResult login() {
        return CommonResult.success().data("token", "admin");
    }

    @ApiOperation("用户登录信息")
    @GetMapping("info")
    public CommonResult info() {
        return CommonResult.success().data("roles", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
