package org.example.controller;

import org.example.dto.AppointMent;
import org.example.dto.Result;
import org.example.enums.TeachCouseEnum;
import org.example.execption.NotFoundId;
import org.example.execption.RepeatAppoint;
import org.example.service.TeachCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-24 23:21
 **/
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired(required = false)
    private TeachCourse teachCourse;

    @RequestMapping(value = "/home",produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result getHome(){
        return new Result(false,"这是测试！");
    }

    @RequestMapping(value = "/appoint",method = RequestMethod.POST,produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    //最后返回的只有result（1.只返回String信息，2.返回预约信息（成功，失败））
    public Result<AppointMent> appoint(@RequestParam("teaid") Long teaId, @RequestParam("couid") Long couId){
        if (teaId==null||couId==null){
            return new Result(false,"id不能为空");
        }
        AppointMent appointMent=null;
        try{
            appointMent=teachCourse.teachCouse(teaId,couId);
        }catch (NotFoundId e1){
            appointMent=new AppointMent(teaId,couId, TeachCouseEnum.ID_NOTFOUND);
        }catch (RepeatAppoint e2){
            appointMent=new AppointMent(teaId,couId,TeachCouseEnum.REAPEAT_APPOINT);
        }catch (Exception e3){
            appointMent=new AppointMent(teaId,couId,TeachCouseEnum.SYSTEM_EXECPTION);
        }
        return new Result(true,appointMent);
    }
}
