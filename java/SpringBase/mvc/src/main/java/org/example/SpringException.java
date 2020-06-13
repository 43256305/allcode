package org.example;

/**
 * @program: mvc
 * @description:
 * @author: xjh
 * @create: 2020-03-22 16:32
 **/
public class SpringException extends RuntimeException{
    private String exceptionMsg;
    public SpringException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
    public String getExceptionMsg(){
        return this.exceptionMsg;
    }
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
