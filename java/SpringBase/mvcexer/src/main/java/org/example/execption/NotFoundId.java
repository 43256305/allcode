package org.example.execption;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-29 20:58
 **/
public class NotFoundId extends RuntimeException {

    public NotFoundId(String message){
        super(message);
    }

    public NotFoundId(String message,Throwable cause){
        super(message,cause);
    }
}
