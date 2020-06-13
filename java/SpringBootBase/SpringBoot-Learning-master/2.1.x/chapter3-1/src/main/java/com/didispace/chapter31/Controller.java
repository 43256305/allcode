package com.didispace.chapter31;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: chapter3-1
 * @description:
 * @author: xjh
 * @create: 2020-04-03 11:09
 **/
@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/students")
    public List<User> getUsers(){
       return userService.getListUsers();
    }

    @GetMapping("/students/{no}")
    public User getUser(@PathVariable("no") Long no){
        User user=userService.getUserByNo(no);
        return user;
    }

    @PostMapping("/students")
    public void add(@RequestBody User user){
        userService.create(user.getName(),user.getAge());
    }
}
