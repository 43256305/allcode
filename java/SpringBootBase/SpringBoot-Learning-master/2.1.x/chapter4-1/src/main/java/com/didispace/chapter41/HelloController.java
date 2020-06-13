package com.didispace.chapter41;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HelloController {

    @Autowired
    StudentFactory studentFactory;

    @GetMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.didispace.com");
        map.addAttribute("students",studentFactory.getStudentList());
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @PostMapping("/")  //前端传来的no
    public String index(ModelMap map,String no){
        map.addAttribute("students",studentFactory.getStudentList());
        return "index";
    }

    //注意图片虽然传进来type，但是还是用get方法
    @GetMapping("/login")
    public String login(int type, HttpSession httpSession){
        if (type==1){
            httpSession.setAttribute("login",true);
        }else{
            httpSession.removeAttribute("login");
        }
        return "index";
    }

}