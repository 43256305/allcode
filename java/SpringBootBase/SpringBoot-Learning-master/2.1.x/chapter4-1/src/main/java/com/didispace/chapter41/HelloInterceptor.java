package com.didispace.chapter41;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @program: chapter4-1
 * @description: 拦截器
 * @author: xjh
 * @create: 2020-03-13 10:35
 **/
@Component
public class HelloInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getParameter("no"));
        return true;  //注意改为true
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ArrayList<Student> students=(ArrayList<Student>) modelAndView.getModel().get("students");
        if (students!=null){
            for (Student s:students){
                System.out.println(s.getName());
            }
        }

    }
}
