package com.didispace.chapter31;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: chapter3-1
 * @description:
 * @author: xjh
 * @create: 2020-04-03 11:47
 **/
@ControllerAdvice
public class ControlerExecptionHandler {
    @ResponseBody
    @ExceptionHandler(StudentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> handleStudnetNotFound(StudentNotFound s){
        Map<String,Object> map=new HashMap<>();
        map.put("errorCode",s.getCode());
        map.put("errorMessage",s.getMessage());
        return map;
    }

    @ResponseBody
    @ExceptionHandler(StudentDuplicated.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,Object> handleStudentDuplicated(StudentDuplicated s){
        Map<String,Object> map=new HashMap<>();
        map.put("errorCode",s.getCode());
        map.put("errorMessage",s.getMessage());
        return map;
    }
}
