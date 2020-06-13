package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //可以看到TextEditor构造器中的输出在这里就执行了，所以所有的bean在这里就被创建了，下面的getBean（）只是获得
        //TextEditor的setSpellChecker注入也是在创建时就执行了（说明setter是在创建时注入）
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld helloWorld=(HelloWorld)context.getBean("helloWorld");
        System.out.println(helloWorld.getMessage1());  //:Hello World!
        System.out.println(helloWorld.getMessage2());  //:Hello Second World!
        HelloIndia helloIndia=(HelloIndia)context.getBean("helloIndia");
        System.out.println(helloIndia.getMessage1());  //:Hello India
        System.out.println(helloIndia.getMessage2());  //:Hello Second World!
        System.out.println(helloIndia.getMessage3());  //:Hello Third India

        System.out.println("---------------------------------------");
        //构造器注入
        TextEditor textEditor=(TextEditor)context.getBean("textEditor");
        textEditor.spellChecker();  //:Inside checkSpelling.
        TextEditor textEditor1=(TextEditor)context.getBean("textEditor1");
        textEditor1.desc();   //:TextEditor'int is 0 string is str

        //setter注入
        TextEditor textEditor2=(TextEditor)context.getBean("textEditor2");
        textEditor2.spellChecker();  //:Inside checkSpelling.

        TextEditor textEditor3=(TextEditor)context.getBean("textEditor3");
        textEditor3.spellChecker();  //:Inside checkSpelling.
    }
}
