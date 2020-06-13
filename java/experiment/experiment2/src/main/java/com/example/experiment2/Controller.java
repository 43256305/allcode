package com.example.experiment2;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: experiment2
 * @description:
 * @author: xjh
 * @create: 2020-04-15 15:37
 **/
@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String getHome(){
        return "index";
    }
}
