package com.didispace.chapter41;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: chapter4-1
 * @description: 拦截器配置
 * @author: xjh
 * @create: 2020-03-13 10:39
 **/
@Configuration
public class HelloWebMvcConfuragurer implements WebMvcConfigurer {
    @Autowired  //自动获取，需要spring生成的都要自动获取
    private HelloInterceptor helloInterceptor;
    @Autowired
    private ImgInterceptor imgInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(helloInterceptor).addPathPatterns("/");
        registry.addInterceptor(imgInterceptor).addPathPatterns("/**.jpg","/**.png");
    }
}
