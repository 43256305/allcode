package com.example.foodorderingsystem.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @program: foodorderingsystem
 * @description:管理员实体
 * @author: xjh
 * @create: 2020-04-04 10:56
 **/
@Data
@Component
public class Manager {
    private String name;
    private String password;
    private Long id;
    private Long telephone;
    private String sex;
}
