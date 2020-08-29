package com.example.seckill.dao;

import com.example.seckill.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDao {

    @Select("SELECT id,productId,amount FROM seckillOrder")
    List<Order> listOrders();

    @Select("SELECT id,productId,amount FROM seckillorder WHERE id=#{id}")
    Order getOrderById(@Param("id") Long id);

    @Insert("INSERT INTO seckillorder(id,productId,amount) VALUES(#{id},#{productId},#{amount})")
    void saveOrder(Order order);

    @Update("UPDATE seckillorder SET productId=#{productId},amount=#{amount} WHERE id=#{id}")
    void updateOrder(Order order);

    @Delete("DELETE FROM seckillorder WHERE id=#{id}")
    void deleteOrder(@Param("id") Long id);


}
