package com.example.foodorderingsystem.pojo;

import lombok.Data;

/**
 * @program: foodorderingsystem
 * @description:   用户，店主，骑手的主类，在用户子系统，骑手子系统，店主子系统中的角色都要继承他
 * @author: xjh
 * @create: 2020-05-10 10:08
 **/
@Data
public class User {
    private Long id;
    private String name;
    private int iden;  //身份表示，1代表用户，2代表店主，3代表骑手
}
