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
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        JavaCollection javaCollection=(JavaCollection)context.getBean("javaCollection");
        javaCollection.getList();  //：List Elements is[INDIA, Pakistan, USA, USA]
        javaCollection.getSet();   //：Set Elements is[INDIA, Pakistan, USA]
        javaCollection.getMap();   //：Map Elements is{1=INDIA, 2=Pakistan, 3=USA, 4=USA}
        javaCollection.getProperties();  //：Properties Elements is{two=Pakistan, one=INDIA, three=USA, four=USA}

        System.out.println("-----------------------------------");
        TextEditor textEditor=(TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();  //:Inside checkSpelling.

        TextEditor textEditor1=(TextEditor) context.getBean("textEditor1");
        textEditor1.spellCheck();  //:Inside checkSpelling.
    }
}
