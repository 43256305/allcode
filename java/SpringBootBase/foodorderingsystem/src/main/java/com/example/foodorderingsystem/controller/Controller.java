package com.example.foodorderingsystem.controller;

import com.example.foodorderingsystem.mapper.ApplicationMapper;
import com.example.foodorderingsystem.mapper.ManagerMapper;
import com.example.foodorderingsystem.mapper.RecordMapper;
import com.example.foodorderingsystem.mapper.UserMapper;
import com.example.foodorderingsystem.pojo.Application;
import com.example.foodorderingsystem.pojo.Manager;
import com.example.foodorderingsystem.pojo.Record;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @program: foodorderingsystem
 * @description:
 * @author: xjh
 * @create: 2020-04-04 10:46
 **/
@org.springframework.stereotype.Controller
@RequestMapping("/api/v1")
@ResponseBody
@Api(tags = "网上订餐系统")
public class Controller {
    @Autowired(required = false)
    private ApplicationMapper applicationMapper;
    @Autowired(required = false)
    private RecordMapper recordMapper;
    @Autowired
    private Manager manager;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private ManagerMapper managerMapper;

    //同意申请
    @PostMapping("/agreeApplication")
    @ApiImplicitParam("申请实体类")
    @ApiOperation("同意申请")
    public void agreeApplication(@RequestParam("id") long id){
        Record record=new Record();
        Application application=applicationMapper.findApplication(id);
        record.setMessage("同意申请");
        record.setApplicantId(application.getApplicantId());
        record.setHandleId(1000000l);
        record.setTimestamp(System.currentTimeMillis());
        userMapper.updateRole(application.getApplicantId(),application.getToIden());
        applicationMapper.deleteApplication(id);
        recordMapper.insertRecord(record);
    }

    //拒绝申请
    @PostMapping("/rejectApplication")
    @ApiImplicitParam("申请实体类")
    @ApiOperation("拒绝申请")
    public void rejectApplication(@RequestParam("id") long id){
        Record record=new Record();
        Application application=applicationMapper.findApplication(id);
        record.setMessage("拒绝申请");
        record.setApplicantId(application.getApplicantId());
        record.setHandleId(1000000l);
        record.setTimestamp(System.currentTimeMillis());
        applicationMapper.deleteApplication(id);
        recordMapper.insertRecord(record);
    }

    @PostMapping("/modify")
    public void modifyProfile(@RequestParam("name") String name,@RequestParam("value") String value,@RequestParam("id") Long id){
        managerMapper.updateProfile(name,value,id);
        if ("name".equals(name)){
            manager.setName(value);
        }else if ("sex".equals(name)){
            manager.setSex(value);
        }else if ("password".equals(name)){
            manager.setPassword(value);
        }else if ("telephone".equals(name)){
            manager.setTelephone(Long.valueOf(value));
        }
    }

    //获得用户申请
    @GetMapping("userapplications/{page}")
    @ApiImplicitParam(name = "page",value = "查看页码")
    @ApiOperation("获得用户申请")
    @ResponseBody
    public void getUserApplication(@PathVariable("page") int page){

    }

    //获得店主申请
    @GetMapping("/ownerapplications/{page}")
    @ApiImplicitParam(name = "page",value = "查看页码")
    @ApiOperation("获得店主申请")
    public void getOwnerApplication(@PathVariable("page") int page){

    }

    //获得骑手申请
    @GetMapping("/riderApplications/{page}")
    @ApiImplicitParam(name = "page",value = "查看页码")
    @ApiOperation("获得骑手申请")
    public void getRiderApplication(@PathVariable("page") int page){

    }
}
