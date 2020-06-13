package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld helloWorld1=(HelloWorld) context.getBean("helloWorld");
        helloWorld1.setMessage("I am obj1");
        helloWorld1.getMessage();  //:singleton时：I am obj1   prototype时 //：I am obj1
        //这里又创建了一个HelloWorld
        HelloWorld helloWorld2=(HelloWorld) context.getBean("helloWorld");
        helloWorld2.getMessage();  //:I am obj1    //：Hello World!
        System.out.println("--------------------------------");

        AbstractApplicationContext context1=new ClassPathXmlApplicationContext("applicationContext.xml");
        InitDestroy initDestroy=(InitDestroy)context1.getBean("initDestroy");
        initDestroy.getMessage();
        //此方法确保正常关闭
        context1.registerShutdownHook();
    }
}
