package org.example;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: annotation
 * @description:
 * @author: xjh
 * @create: 2020-03-19 10:06
 **/
public class Student {
    private Integer age;
    private String name;

    public void setAge(Integer age) {
        this.age = age;
    }
    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
