package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @program: annotation
 * @description:
 * @author: xjh
 * @create: 2020-03-19 12:00
 **/
public class Profile {
    //当student有多个实例时，选择获取其中的一个
    @Autowired
    @Qualifier("student1")
    private Student student;
    public Profile(){
        System.out.println("Inside Profile constructor." );
    }
    public void printAge() {
        System.out.println("Age : " + student.getAge() );
    }
    public void printName() {
        System.out.println("Name : " + student.getName() );
    }
}
