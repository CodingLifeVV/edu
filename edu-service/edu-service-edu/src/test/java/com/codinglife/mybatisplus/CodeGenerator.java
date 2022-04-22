package com.codinglife.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.Test;

/**
 * @author CodingLife
 * @Description Mybatis-Plus 代码自动生成
 * @since 2022/3/1 17:49
 */
//@ConfigurationProperties(prefix = "spring.datasource")
public class CodeGenerator {


    /**
     * 全局配置 Builder
     */
    private final GlobalConfig.Builder globalConfigBuilder;

    /**
     * 包配置 Builder
     */
    private final PackageConfig.Builder packageConfigBuilder;

    /**
     * 策略配置 Builder
     */
    private final StrategyConfig.Builder strategyConfigBuilder;

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder(
                    "jdbc:mysql://localhost:3306/edu?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8",
                    "root",
                    "12345678"
            )
            .build();

    public CodeGenerator() {
        globalConfigBuilder = globalConfig();
        packageConfigBuilder = packageConfig();
        strategyConfigBuilder = strategyConfig();
    }

    /**
     * 全局配置
     * @return
     */
    private GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder()
                .fileOverride()                 //覆盖已生成文件
                .outputDir("/Users/wyj/Desktop/edu-all/edu/edu-service/edu-service-cms" + "/src/main/java") //指定输出目录
                .author("CodingLife")           //作者名
                .enableSwagger()                //开启 Swagger 模式
                .dateType(DateType.TIME_PACK)   //时间策略
                .commentDate("yyyy-MM-dd");     //注释日期
        ///Users/wyj/Desktop/edu-all/edu/edu-service/edu-service-cms
    }

    /**
     * 包配置
     */
    private PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder()
                .parent("com.codinglife"); //父包名
    }

    /**
     * 策略配置
     * @return
     */
    private StrategyConfig.Builder strategyConfig() {
        StrategyConfig.Builder strategyConfigBuilder = new StrategyConfig.Builder();
        strategyConfigBuilder
                .addInclude("crm_banner")       // 设置需要生成的表名
                .addTablePrefix("edu_", "is_")   // 增加过滤表前缀
                .enableCapitalMode();            // 开启大写命名
        // 实体策略配置
        strategyConfigBuilder
                .entityBuilder()
                .enableLombok()                 // 开启 Lombok
                .enableRemoveIsPrefix()         // 开启 Boolean 类型字段移除 is 前缀
                .enableTableFieldAnnotation()   // 开启生成实体时生成字段注解
                .naming(NamingStrategy.underline_to_camel)  // 数据库表映射到实体的命名策略, 默认下划线转驼峰命名: NamingStrategy.underline_to_camel
                .versionColumnName("version")   // 乐观锁字段名(数据库)
                .versionPropertyName("version") // 乐观锁属性名(实体)
                .logicDeleteColumnName("is_deleted")        // 逻辑删除字段名(数据库)
                .logicDeletePropertyName("deleted")         // 逻辑删除属性名(实体)
                .addTableFills(new Column("gmt_create", FieldFill.INSERT), // 添加表字段填充
                        new Column("gmt_modified", FieldFill.INSERT_UPDATE));
        // Service 策略配置
        strategyConfigBuilder
                .serviceBuilder()
                .formatServiceFileName("%sService");         // 去掉 Service 接口的首字母 I (默认)
                //.formatServiceImplFileName("%sServiceImp"); // 去掉 Service 实现类的首字母 I
        return strategyConfigBuilder;
    }

    @Test
    public void generator() {
        new AutoGenerator(this.DATA_SOURCE_CONFIG)
                // 全局配置
                .global(this.globalConfigBuilder.build())
                // 包配置
                .packageInfo(this.packageConfigBuilder.build())
                // 策略配置
                .strategy(this.strategyConfigBuilder.build())
                // 执行
                .execute();
    }

}
