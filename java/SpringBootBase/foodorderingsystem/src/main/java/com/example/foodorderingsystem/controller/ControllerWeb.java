package com.example.foodorderingsystem.controller;

import com.example.foodorderingsystem.mapper.ApplicationMapper;
import com.example.foodorderingsystem.mapper.ManagerMapper;
import com.example.foodorderingsystem.mapper.RecordMapper;
import com.example.foodorderingsystem.mapper.UserMapper;
import com.example.foodorderingsystem.pojo.Manager;
import com.example.foodorderingsystem.pojo.Record;
import com.example.foodorderingsystem.pojo.User;
import com.example.foodorderingsystem.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: foodorderingsystem
 * @description:
 * @author: xjh
 * @create: 2020-04-10 11:30
 **/
@Controller
public class ControllerWeb {

    @Autowired(required = false)
    private ManagerService managerService;
    @Autowired(required = false)
    private ApplicationMapper applicationMapper;
    @Autowired(required = false)
    private RecordMapper recordMapper;
    @Autowired
    private Manager manager;
    @Autowired(required = false)
    private ManagerMapper managerMapper;
    @Autowired(required = false)
    private UserMapper userMapper;


    @GetMapping("/")
    public String getHome(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") Long id, @RequestParam("userPassword") String password){
        if (managerService.login(id,password)){
            Manager manager1=managerMapper.Login(id,password);
            manager.setId(manager1.getId());
            manager.setName(manager1.getName());
            manager.setPassword(manager1.getPassword());
            manager.setSex(manager1.getSex());
            manager.setTelephone(manager1.getTelephone());
            return "home";
        }else{
            return "login";
        }
    }

    //返回投诉页面
    @GetMapping("/complaint")
    public String getComplaint(Model model){
        List<Record> record=recordMapper.findAll();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (record!=null){
            for (int i=0;i<record.size();i++){
                Record r=record.get(i);
                String time=simpleDateFormat.format(new Date(r.getTimestamp()));
                r.setTime(time);
            }
        }
        model.addAttribute("record",record);
        return "complaint";
    }

    //返回申请页面
    @GetMapping("/application")
    public String getApplication(Model model){
        model.addAttribute("list",applicationMapper.findAll());
        return "application";
    }

    //返回个人信息页面
    @GetMapping("/profile")
    public String getProfile(Model model){
        model.addAttribute("manager",manager);
        return "profile";
    }

    @GetMapping("/modifyProfile")
    public String getModify(Model model,@RequestParam("name") String name,@RequestParam("value") String value,@RequestParam("id") Long id){
        model.addAttribute("name",name);
        model.addAttribute("value",value);
        model.addAttribute("id",id);
        return "modifyProfile";
    }

    @PostMapping("/user")
    @ApiOperation("搜索用户")
    public String searchUser(@RequestParam("id") Long id, Model model){
        User user=userMapper.searchUser(id);
        if (user==null){
            return "home";
        }
        model.addAttribute("user",user);
        return "userprofile";
    }

    //查看餐馆主页，店主提供的接口
    @GetMapping("/restaurant/{id}")
    public String getRestaurant(@PathVariable long id){return "restarant";}

}
