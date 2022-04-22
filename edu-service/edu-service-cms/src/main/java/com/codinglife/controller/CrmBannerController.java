package com.codinglife.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codinglife.CommonResult;
import com.codinglife.entity.CrmBanner;
import com.codinglife.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * banner后台管理接口 首页轮播图
 * </p>
 *
 * @author CodingLife
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/educms/banner")
@CrossOrigin
public class CrmBannerController {
    @Autowired
    private CrmBannerService crmBannerService;

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping()
    public CommonResult pageBanner(@PathVariable long page, long limit){
        Page<CrmBanner> bannerPage = new Page<>(page,limit);
        IPage<CrmBanner> pages = crmBannerService.page(bannerPage, null);
        List<CrmBanner> records = pages.getRecords();
        long total = pages.getTotal();
        return CommonResult.success().data("items",records).data("total",total);
    }

    /**
     * 添加 banner
     * @param crmBanner
     * @return
     */
    @PostMapping("addBanner")
    public CommonResult addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return CommonResult.success();
    }

    /**
     * 根据id获取banner
     * @param id
     * @return
     */
    @ApiOperation(value = "获取Banner")
    @GetMapping("getBanner/{id}")
    public CommonResult get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return CommonResult.success().data("item", banner);
    }

    /**
     * 修改 banner
     * @param banner
     * @return
     */
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public CommonResult updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return CommonResult.success();
    }

    /**
     * 根据id删除banner
     * @param id
     * @return
     */
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public CommonResult remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return CommonResult.success();
    }

}

