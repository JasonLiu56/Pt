package com.jxcia.pt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                //.enable(false)
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com.jxcia.pt.controller"))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .pathMapping("/")
                .globalOperationParameters(this.getParameters());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("教学平台Swagger2接口")
                //文档描述
                .description("接口说明")
                //服务条款URL
                .termsOfServiceUrl("http://www.pt.com:8080/")
                //版本号
                .version("1.0.0")
                .build();
    }

    private List<Parameter> getParameters() {
        ParameterBuilder tokenParam = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        tokenParam.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        params.add(tokenParam.build());
        return params;
    }

}
