package com.didispace.chapter41;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @program: webassignment2
 * @description: 生成Arraylist的Student
 * @author: xjh
 * @create: 2020-03-11 11:02
 **/
@Component
public class StudentFactory {
    public ArrayList<Student> getStudentList(){
        ArrayList<Student> students=new ArrayList<>();
        students.add(new Student("jack",1,20));
        students.add(new Student("mike",2,21));
        students.add(new Student("xie",3,22));
        return students;
    }
}
