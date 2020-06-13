package com.example.experiment3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: experiment3
 * @description:
 * @author: xjh
 * @create: 2020-04-22 13:58
 **/
@Controller
public class HomeController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/home").setViewName("welcome");
    }

    @GetMapping("/")
    public ModelAndView getLogin(){
        User u=new User();
        ModelAndView modelAndView=new ModelAndView("login");
        modelAndView.addObject("user",u);
        return modelAndView;
    }

    @PostMapping("/login")
    public String check(@Validated User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute(user);
            return "login";
        }else
            return "home";
    }

}

