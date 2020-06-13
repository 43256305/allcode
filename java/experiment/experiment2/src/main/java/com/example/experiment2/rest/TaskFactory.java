package com.example.experiment2.rest;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: experiment2
 * @description:
 * @author: xjh
 * @create: 2020-04-15 16:09
 **/
@Component
public class TaskFactory {
    private List<Task> tasks;

    public TaskFactory(){
        tasks=new ArrayList<>();
        tasks.add(new Task(1,"谢杰辉"));
        tasks.add(new Task(2,"辉杰谢"));
    }

    public Task get(int id){
        for (Task t:tasks){
            if (t.getId()==id){
                return t;
            }
        }
        return null;
    }
}
