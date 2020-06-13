package com.example.restfulexer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: restfulexer
 * @description:
 * @author: xjh
 * @create: 2020-03-25 11:00
 **/
@Configuration
//EnableSwagger在这里写了就不用再主类写了
@EnableSwagger2
//与chapter2.2不一样，chapter2.2是直接到application中配置，而这里写了一个bean
public class SwaggerUiConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("网上订餐系统")
                .description("网上订餐系统RESTful api")
                .version("1.0")
                .build();
    }
}
