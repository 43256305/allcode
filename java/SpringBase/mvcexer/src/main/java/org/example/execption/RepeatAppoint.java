package org.example.execption;

/**
 * @program: mvcexer
 * @description: 重复预约
 * @author: xjh
 * @create: 2020-03-29 21:01
 **/
public class RepeatAppoint extends RuntimeException {
    public RepeatAppoint(String message){
        super(message);
    }

    public RepeatAppoint(String message,Throwable cause){
        super(message, cause);
    }
}
