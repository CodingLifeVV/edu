package com.codinglife.service;

import com.codinglife.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.boot.Banner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author CodingLife
 * @since 2022-04-21
 */
public interface CrmBannerService extends IService<CrmBanner> {
    List<CrmBanner> getAllBanner();

    void savaBanner(CrmBanner crmBanner);

    void updateBannerById(CrmBanner crmBanner);

    void removeBannerById(String id);
}
