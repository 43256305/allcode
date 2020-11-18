package com.example.Controller;

import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: xjh
 * @create: 2020-11-16 16:59
 **/
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/dept")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/{deptno}")
    public Dept getDept(@PathVariable("deptno") Long deptno){
        return deptService.queryById(deptno);
    }

    @GetMapping("/dept")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    @GetMapping("/info")
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        System.out.println(list);
        List<ServiceInstance> instances = discoveryClient.getInstances("springcloud-provider-dept");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getHost()+instance.getPort()+instance.getUri());
        }
        return discoveryClient;
    }
}
