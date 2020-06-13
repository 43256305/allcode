package org.example;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
//spring中容器管理bean的整个生命周期从创建到销毁
//spirng中有两个容器：BeanFactory和ApplicationContext
public class App 
{
    public static void main( String[] args ) {
        //ApplicationContext容器包含了BeanFactory的全部功能，更常用
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("applicationContext.xml");

        //使用App容器的另一个接口（有3个接口）  生成工厂对象-加载xml文件-利用接口生成工厂bean
        ApplicationContext context =new FileSystemXmlApplicationContext
                ("D:\\源码\\java\\SpringBase\\hellospring\\src\\main\\resources\\applicationContext.xml");

        //这个容器更轻量
//        XmlBeanFactory context=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));

        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
    }
}
