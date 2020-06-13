package org.example.dto;

import java.io.Serializable;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-29 20:44
 **/
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5054749880960511861L;
    private T data;  //controller返回给前端的数据
    private boolean success;  //是否成功
    private String message;

    //失败的构造器
    public Result(boolean failure,String message){
        this.message=message;
        this.success=failure;
    }

    public Result(boolean success,T data){
        this.data=data;
        this.success=success;
    }
}
