package com.example.restfulexer;


/**
 * @program: restfulexer
 * @description: 定义学生没有找到抛出的异常
 * @author: xjh
 * @create: 2020-03-25 10:40
 **/
public class DemoException extends Exception {
    public int getErrorCode(){
        return 101;
    }

    public String getErrorMessage(){
        return "Student no found!";
    }
}
