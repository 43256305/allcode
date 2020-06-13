package com.example.foodorderingsystem.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: foodorderingsystem
 * @description:审核记录
 * @author: xjh
 * @create: 2020-04-04 15:58
 **/
@Data
public class Record {
    private Long id;   //记录id
    private Long timestamp;  //处理时间戳
    private Long applicantId;  //申请人   applicant_id
    private String message;  //信息
    private Long handleId;  //处理人id   handler_id
    private String time;
}
