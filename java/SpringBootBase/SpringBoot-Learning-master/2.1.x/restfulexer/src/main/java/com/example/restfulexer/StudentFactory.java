package com.example.restfulexer;

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
    private ArrayList<Student> students;

    public StudentFactory(){
        ArrayList<Student> students=new ArrayList<>();
        students.add(new Student("jack",1l,20));
        students.add(new Student("mike",2l,21));
        students.add(new Student("xie",3l,22));
        this.students=students;
    }
    public ArrayList<Student> getStudentList(){
        return students;
    }

    public Student findByNo(Long no) throws DemoException{
        Student student=null;
        for (Student s:students){
            if (s.getId().equals(no)){
                student=s;
            }
        }
        if (student!=null){
            return student;
        }else{
            throw new DemoException();
        }
    }

}
