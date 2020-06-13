package com.didispace.chapter31;

/**
 * @program: chapter3-1
 * @description:
 * @author: xjh
 * @create: 2020-04-03 12:07
 **/
public class StudentDuplicated extends StudentExecption {
    public StudentDuplicated(){
        super.setCode(102);
        super.setMessage("学生信息重复....");
    }
}
