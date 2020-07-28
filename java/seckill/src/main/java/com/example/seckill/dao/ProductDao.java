package com.example.seckill.dao;

import com.example.seckill.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: seckill
 * @description:
 * @author: xjh
 * @create: 2020-07-28 09:53
 **/
@Mapper
public interface ProductDao {

    @Select("SELECT id,name,price,stock,pic FROM product WHERE id=#{id}")
    Product getProductById(@Param("id") Long id);
}
