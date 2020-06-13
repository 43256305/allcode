package com.example.experiment2.rest;

/**
 * @program: experiment2
 * @description:
 * @author: xjh
 * @create: 2020-04-15 15:26
 **/
public class Task {
    private int id;
    private String name;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
