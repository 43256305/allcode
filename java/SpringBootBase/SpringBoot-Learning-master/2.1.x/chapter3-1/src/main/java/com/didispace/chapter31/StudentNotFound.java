package com.didispace.chapter31;

/**
 * @program: chapter3-1
 * @description:
 * @author: xjh
 * @create: 2020-04-03 11:41
 **/
public class StudentNotFound extends StudentExecption {
    public StudentNotFound(){
        super.setCode(101);
        super.setMessage("Student not found....");
    }
}
