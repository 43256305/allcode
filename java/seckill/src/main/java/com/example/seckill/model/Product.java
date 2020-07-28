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
public class Product implements Serializable {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String pic;
}
