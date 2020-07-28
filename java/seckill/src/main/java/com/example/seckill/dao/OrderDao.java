package com.example.seckill.dao;

import com.example.seckill.model.Order;
import org.apache.ibatis.annotations.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@Mapper
public interface OrderDao {

    @Select("SELECT id,productid,amount FROM seckillOrder")
    List<Order> listOrders();

    @Select("SELECT id,productid,amount FROM seckillorder WHERE id=#{id}")
    Order getOrderById(@Param("id") Long id);

    @Insert("INSERT INTO seckillorder(id,productid,amount) VALUES(#{id},#{productid},#{amount})")
    void saveOrder(Order order);

    @Update("UPDATE seckillorder SET productid=#{productid},amount=#{amount} WHERE id=#{id}")
    void updateOrder(Order order);

    @Delete("DELETE FROM seckillorder WHERE id=#{id}")
    void deleteOrder(@Param("id") Long id);


}
