package org.example;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: mvc
 * @description:
 * @author: xjh
 * @create: 2020-03-22 15:10
 **/
@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String home(Model model){
        model.addAttribute("message","this is Hello World page");
        return "index";
    }

    @RequestMapping(value = "/students",method = RequestMethod.GET)
    public ModelAndView studnet(){
//        如果前端使用<form:from>标签，就需要一个名称为command的对象（Student类）
        return new ModelAndView("student","command",new Student());
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
//    ModelAttribute是从form或者url中获取Student对象  从student.jsp中获取，到result.jsp中显示
    //RequestBody是从json中获取对象
    public String addStudent(@ModelAttribute Student student, Model model){
        model.addAttribute("id",student.getId());
        model.addAttribute("name",student.getName());
        model.addAttribute("age",student.getAge());
        return "result";
    }

    @RequestMapping(value = "/addStudentExce",method = RequestMethod.POST)
    //异常处理
    @ExceptionHandler(SpringException.class)
    public String addStudent(@ModelAttribute Student student, ModelMap modelMap){
        if (student.getName().length()<5){
            throw new SpringException("Given name is too short");
        }else{
            modelMap.addAttribute("name",student.getName());
        }
        if (student.getAge()<10){
            throw new SpringException("Given age is too low");
        }else{
            modelMap.addAttribute("age",student.getAge());
        }
        modelMap.addAttribute("id",student.getId());
        return "result";
    }

}
