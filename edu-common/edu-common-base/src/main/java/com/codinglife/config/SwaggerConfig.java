package com.codinglife.config;


import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/3 11:08
 */
@Configuration
public class SwaggerConfig {

    /**
     * Swagger 配置信息
     */
    public static final String VERSION = "1.0.0";
    public static final String TITLE = "在线教育网API文档";
    public static final String DESCRIPTION = "课程中心服务接口相关定义";
    public static final String NAME = "CodingLifeV";
    public static final String URL = "https://www.bmrs.top";
    public static final String EMAIL = "wyj_program@163.com";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()           // 初始化并返回一个 API 选择构造器
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  // 添加 ApiOperiation 注解的被扫描
                //.paths(Predicates.not(PathSelectors.regex("/error.*")))         // 设置路径筛选
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.TITLE)
                .description(this.DESCRIPTION)
                .version(this.VERSION)
                .contact(new Contact(this.NAME, this.URL, this.EMAIL))  // 联系人信息
                .build();
    }

}
