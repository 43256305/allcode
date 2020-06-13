package com.example.foodorderingsystem.service;

import com.example.foodorderingsystem.mapper.ManagerMapper;
import com.example.foodorderingsystem.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: foodorderingsystem
 * @description:
 * @author: xjh
 * @create: 2020-04-04 19:43
 **/
@Component
public class ManagerService {
    @Autowired(required = false)
    private ManagerMapper managerMapper;

    private Manager manager;

    public boolean login(Long id,String password){
        manager=managerMapper.Login(id,password);
        if (manager==null){
            return false;
        }
        return true;
    }

    public void logout(){

    }
}
