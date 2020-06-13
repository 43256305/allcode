package com.example.restfulexer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: restfulexer
 * @description: 定义异常处理类
 * @author: xjh
 * @create: 2020-03-25 10:45
 **/
//可以直接抛出异常，为什么还要handle类呢？因为web应用中，所有的返回都是response，所以需要把异常转换为response类型
@ControllerAdvice  //自动捕获controller中出现的异常
public class DemoControllerExceHandle  {

    @ResponseBody  //返回json类型
    @ExceptionHandler(DemoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  //映射到http状态码
    public Map<String,Object> handleStudentNotFound(DemoException ex){
        Map<String,Object> map=new HashMap<>();
        map.put("errorCode",ex.getErrorCode());
        map.put("errorMessage",ex.getErrorMessage());
        return  map;
    }
}
