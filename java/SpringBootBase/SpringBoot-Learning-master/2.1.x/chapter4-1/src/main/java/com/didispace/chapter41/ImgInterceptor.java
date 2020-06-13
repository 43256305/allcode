package com.didispace.chapter41;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: chapter4-1
 * @description: 图片拦截器
 * @author: xjh
 * @create: 2020-03-13 11:20
 **/
@Component
public class ImgInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return request.getSession().getAttribute("login")!=null;
    }
}
