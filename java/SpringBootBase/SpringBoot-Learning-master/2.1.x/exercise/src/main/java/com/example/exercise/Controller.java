package com.example.exercise;

import org.apache.ibatis.annotations.Delete;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: exercise
 * @description:
 * @author: xjh
 * @create: 2020-03-14 21:21
 **/

@org.springframework.stereotype.Controller
public class Controller {
    @Resource
    private UserMapper userMapper;

    //Model与ModelMap用法差不多（系统会自动传递给controller方法），ModelAndView则需要用户自己new
    //ModelAndView默认使用转发
    @GetMapping("/")
    public ModelAndView getAllUsers(){
        ModelAndView modelAndView=new ModelAndView("input");  //传递要显示的页面名称，虽然new了对象，但这是转发
        modelAndView.addObject("list",userMapper.getAllUsers());  //传递数据
        return modelAndView;
    }

    @GetMapping("/table")
    public String getTable(Model model){
        model.addAttribute("list",userMapper.getAllUsers());
        return "home";
    }

    @PostMapping("/insert")
    public ModelAndView addUser(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("age") Integer age){
        userMapper.insert(id,name,age);
        //用了重定向就不会重新提交表单了
        ModelAndView modelAndView=new ModelAndView("redirect:/"); //重定向到了getAllUsers()方法（重定向发送两次请求，转发是一次请求）
        modelAndView.addObject("list",userMapper.getAllUsers());
        return modelAndView;
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id,Model model){
        userMapper.deleteById(id);
        model.addAttribute("list",userMapper.getAllUsers());
        return "home";
    }

    @PutMapping("/put")
    public String updateUser(@RequestParam("id") Long id,@RequestParam("name") String name,@RequestParam("age") Integer age,Model model){
        userMapper.updateById(id,name,age);
        model.addAttribute("list",userMapper.getAllUsers());
        return "home";
    }
}
