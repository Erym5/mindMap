package com.example.mindmap.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenApiConfig {

    @Value("${swagger_is_enable}")
    private boolean swagger_is_enable;
    @Bean
    Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .enable(swagger_is_enable)
                //配置网站的基本信息
                .apiInfo(new ApiInfoBuilder()
                        //网站标题
                        .title("MindMap项目在线接口文档")
                        //标题后面的版本号
                        .version("v1.0")
                        .description("bookMap项目接口文档")
                        //联系人信息
                        .contact(new Contact("H*J", "123", "111@qq.com"))
                        .build())
                .select()
                //指定接口的位置
                .apis(RequestHandlerSelectors.basePackage("com.example.mindmap.controller"))
                .build();
    }
}
