package com.example.seckill.common;

/**
 * @program: seckill
 * @description:  常量类，提供常用常量
 * @author: xjh
 * @create: 2020-08-10 22:51
 **/
public class Constants {
    //prefix：前缀
    public static final String REDIS_PRODUCT_PREFIX = "product_";
    public static final String REDIS_PRODUCT_STOCK_PREFIX = "product_stock_";
    public static final String ZK_PRODUCT_SOLD_OUT_FLAG = "/product_sold_out_flag";
    public static String getZkPRODUCTSoldOutPath(Long id){
        return ZK_PRODUCT_SOLD_OUT_FLAG+"/"+id;
    }
}
