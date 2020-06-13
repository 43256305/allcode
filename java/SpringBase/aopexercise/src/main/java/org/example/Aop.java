package org.example;

import org.aspectj.lang.annotation.*;

/**
 * @program: aopexercise
 * @description:
 * @author: xjh
 * @create: 2020-03-21 20:33
 **/
@Aspect
public class Aop {
    @Pointcut("execution(* org.example.HelloWorld.desc())")
    public void selectHello(){}

    @After("selectHello()")
    public void after(){
        System.out.println("after");
    }

    @Before("selectHello()")
    public void before(){
        System.out.println("before");
    }

    @AfterReturning(value = "selectHello()",returning = "o")
    public void Returning(Object o){
        System.out.println("returning:"+o.toString());
    }
}
