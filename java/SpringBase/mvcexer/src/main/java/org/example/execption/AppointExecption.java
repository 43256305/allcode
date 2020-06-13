package org.example.execption;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-29 21:03
 **/
//这三个异常并不会传递给前端，只是给controller做了个判断，在AppointMent中包装了信息返回给前端
public class AppointExecption extends RuntimeException {
    public AppointExecption(String message){
        super(message);
    }
    public AppointExecption(String message,Throwable cause){
        super(message, cause);
    }
}
