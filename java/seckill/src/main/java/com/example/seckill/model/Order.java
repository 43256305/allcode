package com.example.seckill.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: seckill
 * @description:
 * @author: xjh
 * @create: 2020-07-28 09:21
 **/
@Data
public class Order implements Serializable {
    private Long id;
    private Long productId;
    private BigDecimal amount;
}
