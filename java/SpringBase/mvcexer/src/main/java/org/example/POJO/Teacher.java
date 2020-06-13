package org.example.POJO;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-24 23:19
 **/
public class Teacher {
    private long id;
    private String name;
    private String password;

    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
