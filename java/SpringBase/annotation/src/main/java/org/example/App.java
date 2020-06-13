package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
        TextEditor textEditor=(TextEditor)context.getBean("textEditor");
        textEditor.spellCheck();  //:Inside checkSpelling.

        Profile profile=(Profile)context.getBean("profile");
        profile.printName();  //:Name : xie

        //这里获取bean都是用.class，而不是字符串
        AbstractApplicationContext context1=new AnnotationConfigApplicationContext(HelloWorldConfig.class);
        HelloWorld helloWorld=(HelloWorld)context1.getBean(HelloWorld.class);
        System.out.println(helloWorld.getMessage());  //:hello world!
        helloWorld.descLittleWorld();   //:This is little world!

        //这里没有输出初始化语句，说明只有一个helloworld实例，再获取bean，获取到的也是同一个实例
        HelloWorld helloWorld1=(HelloWorld)context1.getBean(HelloWorld.class);

        //用import引入不属于HelloWorldConfig中的bean，也就是说如果有多个config类，通过import，我们可以只new一个context来调用所有bean
        AnotherHello anotherHello=(AnotherHello)context1.getBean(AnotherHello.class);
        //这个关闭方法AbstractApplicationContext类有，普通的ApplicationContext没有
        context1.registerShutdownHook();
    }
}
