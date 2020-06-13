package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
//aop有各种各样的常见的很好的方面的例子，如日志记录、审计、声明式事务、安全性和缓存等。在 OOP 中，关键单元模块度是类，而在 AOP 中单元模块度是方面
// Spring AOP 模块提供拦截器来拦截一个应用程序
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        //getName有两个aspect，一个是selectAll，一个是selectName，因为All在Name前面，所以先执行All中的advice，再执行Name中的
        student.getName();
        student.getAge();
        student.printThrowException();   //这里会抛出一个异常
    }
}
