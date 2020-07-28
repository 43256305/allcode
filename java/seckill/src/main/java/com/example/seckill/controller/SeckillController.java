package com.example.seckill.controller;

import com.example.seckill.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @program: seckill
 * @description:
 * @author: xjh
 * @create: 2020-07-28 09:59
 **/
@Controller
public class SeckillController {

    @Autowired
    private OrderService orderService;

    private Logger logger = LoggerFactory.getLogger(SeckillController.class);



    @PostMapping("/{productId}")
    public void seckill(@PathVariable("productId") Long productId){
        try{
            orderService.seckill(productId);
        }catch (Exception e){
            logger.error("创建订单失败",e);
        }

    }
}
