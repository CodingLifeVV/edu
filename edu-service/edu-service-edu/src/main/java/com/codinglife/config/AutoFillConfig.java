package com.codinglife.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * @author CodingLife
 * @Description 自动填充属性配置类
 * @since 2022/3/4 16:16
 */
@Component
public class AutoFillConfig implements MetaObjectHandler {
    private static final Logger log =
            LoggerFactory.getLogger(AutoFillConfig.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 插入时自动填充属性 gmtCreate 创建时间
        this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, LocalDateTime.now());
        // 插入时自动填充属性 gmtModified 更新时间
        this.strictInsertFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
        // 插入时自动填充属性 deleted
        this.strictInsertFill(metaObject, "deleted", Boolean.class, false);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 更新时自动填充属性 gmtModified
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
        // 更新时自动填充属性 version
        //this.strictUpdateFill(metaObject, "version", Integer.class, 1);
    }
}
