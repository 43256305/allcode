package com.example.restfulexer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: restfulexer
 * @description:
 * @author: xjh
 * @create: 2020-03-20 10:33
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Api(tags = "学生管理")
public class Controller {
    @Autowired
    private StudentFactory studentFactory;

    @GetMapping("/msg")
    @ApiOperation(value = "测试",notes = "hello world测试")
    //表示方法被废止
    @Deprecated
    public String hello(){
        return "hello world";
    }

    //注意这里students后面要不要加/，（不加浏览器加不加都可以，加了，浏览器一定要加）
    @GetMapping("/students/")
    @ApiOperation(value = "查看全体学生",notes = "查看全体学生信息")
    public List<Student> getStudents(){
        return studentFactory.getStudentList();
    }

    @GetMapping("/students/{no}")
    @ApiOperation(value = "获取学生",notes = "根据no获取某个学生信息")
    public Student getStudent(@PathVariable("no") Long no) throws DemoException{
        return studentFactory.findByNo(no);
    }
}
