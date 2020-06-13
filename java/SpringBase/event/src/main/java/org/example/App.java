package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        context.start();  //:ContextStartedEvent Received
        HelloWorld helloWorld=(HelloWorld) context.getBean("helloWorld");
        System.out.println(helloWorld.getMessage() );  //:Hello World!
        context.stop();  //:ContextStartedEvent Received

        System.out.println("--------------------------------------");
        //自定义事件
        CustomEventPublisher cvp =
                (CustomEventPublisher) context.getBean("customEventPublisher");
        cvp.publish();
        cvp.publish();
    }
}
