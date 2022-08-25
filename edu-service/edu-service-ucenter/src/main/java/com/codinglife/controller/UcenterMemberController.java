package com.codinglife.controller;


import com.codinglife.CommonResult;
import com.codinglife.entity.UcenterMember;
import com.codinglife.entity.vo.LoginVo;
import com.codinglife.entity.vo.RegisterVo;
import com.codinglife.jwt.JwtUtils;
import com.codinglife.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author CodingLife
 * @since 2022-04-25
 */
@Controller
@RequestMapping("/ucenterMember/apimember")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    /**
     * 使用手机号和密码登陆
     * @param loginVo
     * @return
     */
    @PostMapping("login")
    public CommonResult login(@RequestBody LoginVo loginVo){
        String token =ucenterMemberService.login(loginVo);
        return CommonResult.success().data("token",token);
    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @PostMapping("register")
    public CommonResult register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return CommonResult.success();
    }

    /**
     * 根据token获取用户信息
     * @param request
     * @return
     */
    @GetMapping("getMemberInfo")
    public CommonResult getMemberInfo(HttpServletRequest request){
        //调用jwt工具类，获取头部信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据id获得用户信息
        UcenterMember member = ucenterMemberService.getById(memberId);
        return CommonResult.success().data("userInfo",member);
    }




}

