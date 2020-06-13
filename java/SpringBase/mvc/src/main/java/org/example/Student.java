package org.example;

/**
 * @program: mvc
 * @description:
 * @author: xjh
 * @create: 2020-03-22 15:44
 **/
public class Student {
    private Integer age;
    private String name;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
