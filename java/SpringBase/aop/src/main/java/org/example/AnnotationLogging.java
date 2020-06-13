package org.example;

import org.aspectj.lang.annotation.*;

/**
 * @program: aop
 * @description:
 * @author: xjh
 * @create: 2020-03-19 17:11
 **/
//用注释的方法声明Aspect
//应用程序可以拥有任意数量的方面，这取决于需求。
//advice(Before,After等就是advice):这是实际行动之前或之后执行的方法。这是在程序执行期间通过 Spring AOP 框架实际被调用的代码
@Aspect
public class AnnotationLogging {
    //注意*空格要加
    @Pointcut("execution(* org.example.Student.getName())")
    public void selectName(){}

    @Before("selectName()")
    public void beforeAdvice(){
        System.out.println("Annotation:Going to setup student profile.");
    }

    @After("selectName()")
    public void afterAdvice(){
        System.out.println("Annotation:Student profile has been setup.");
    }

    //记得有值返回值传入此方法
    @AfterReturning(pointcut = "selectName()", returning = "retVal")
    public void afterReturning(Object retVal){
        System.out.println("Annotation:Returning:" + retVal.toString() );
    }
}
