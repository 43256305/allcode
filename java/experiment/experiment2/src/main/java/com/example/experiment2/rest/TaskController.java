package com.example.experiment2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: experiment2
 * @description:
 * @author: xjh
 * @create: 2020-04-15 15:26
 **/
@RestController
public class TaskController {

    @Autowired
    private TaskFactory tasks;

    @PostMapping("/task/{id}")
    public Task task(@PathVariable(value="id") int id) {
        return tasks.get(id);
    }
}
