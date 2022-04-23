package com.codinglife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codinglife.entity.CrmBanner;
import com.codinglife.mapper.CrmBannerMapper;
import com.codinglife.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表(轮播图) 服务实现类
 * </p>
 *
 * @author CodingLife
 * @since 2022-04-21
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * 查询所有banner
     * @Cacheable 根据方法对其返回结果进行缓存 value 缓存名 key 自定义缓存 key
     * @return
     */
    @Cacheable(value = "banner",key = "'bannerList'")
    @Override
    public List<CrmBanner> getAllBanner() {
        //根据id降序排列，显示排列之后的前两条记录
        QueryWrapper<CrmBanner> bannerQueryWrapper = new QueryWrapper<>();
        bannerQueryWrapper.orderByDesc("id");
        //list拼接sql语句
        bannerQueryWrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }

    /**
     * 保存banner
     * @param crmBanner
     * @CacheEvict 清空指定的缓存。一般用在更新或者删除方法上 value 缓存名 allEntries 是否清空所有缓存
     */
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void savaBanner(CrmBanner crmBanner) {
        baseMapper.updateById(crmBanner);
    }

    /**
     * 根据id更新banner
     * @param crmBanner
     */
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void updateBannerById(CrmBanner crmBanner) {
        baseMapper.updateById(crmBanner);
    }

    /**
     * 移除banner
     * @param id
     */
    @CacheEvict(value = "banner", allEntries=true)
    @Override
    public void removeBannerById(String id) {
        baseMapper.deleteById(id);
    }
}
