package com.example.seckill.dao;

import com.example.seckill.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("SELECT id,name,price,stock,pic FROM product")
    List<Product> listProducts();

    //尽量在数据库层面操作数据，在java层面有并发问题（数据库会自动帮我们上锁），操纵时stock要大于0
    @Update("UPDATE product SET stock = stock - 1 WHERE id = #{id} and stock>0")
    int deductProductStock(@Param("id") Long id);

    @Insert("INSERT INTO product(id,name,price,stock,pic) VALUES(#{id}, #{name}, #{price}, #{stock}, #{pic})")
    void saveProduct(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void deleteProduct(Long id);

    @Update("UPDATE product SET name=#{name}, price=#{price} ,stock=#{stock} ,pic=#{pic} WHERE id=#{id}")
    void updateProduct(Product product);
}
