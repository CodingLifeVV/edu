package com.codinglife.controller;

import com.codinglife.CommonResult;
import com.codinglife.entity.CrmBanner;
import com.codinglife.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: Banner 前台查询接口
 * @author: CodingLifeVV
 * @date: 2022.04.22
 */

@RestController
@RequestMapping("/educms/bannerfront")
@Api("网站首页Banner列表")
@CrossOrigin
public class BannerApiController {
    @Autowired
    private CrmBannerService crmBannerService;

    /**
     * 获取首页banner
     * @return
     */
    @GetMapping("getAllBanner")
    @ApiOperation(value = "获取首页banner")
    public CommonResult getAllBanner(){
        List<CrmBanner> List = crmBannerService.getAllBanner();
        return CommonResult.success().data("list",List);
    }
}
