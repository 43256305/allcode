package com.example.foodorderingsystem.pojo;

import lombok.Data;

/**
 * @program: foodorderingsystem
 * @description: 申请
 * @author: xjh
 * @create: 2020-04-04 19:50
 **/
@Data
public class Application {
    private Long id;
    private Long applicantId;  //申请人  user_id
    private int iden;  //申请人身份
    private int toIden;  //要申请的身份

}
