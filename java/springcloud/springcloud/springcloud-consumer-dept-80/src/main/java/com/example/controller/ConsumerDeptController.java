package com.example.controller;

import com.example.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: xjh
 * @create: 2020-11-16 17:25
 **/
@RestController
public class ConsumerDeptController {
    @Autowired
    private RestTemplate restTemplate;
    //(url,实体,Class<T> responseType)

    private static final String REST_URL_PREFIX = "http://localhost:8001";

    @PostMapping("/consumer/dept")
    public boolean addDept(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept",dept,Boolean.class);
    }

    @GetMapping("/consumer/dept/{deptno}")
    public Dept getDeptById(@PathVariable("deptno") Long deptno){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/"+deptno,Dept.class);
    }

    @GetMapping("/consumer/dept")
    public List<Dept> queryAll(){
        System.out.println("热部署");
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept",List.class);
    }


}
