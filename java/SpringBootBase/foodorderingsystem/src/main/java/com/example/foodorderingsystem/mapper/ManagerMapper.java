package com.example.foodorderingsystem.mapper;

import com.example.foodorderingsystem.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @program: foodorderingsystem
 * @description:
 * @author: xjh
 * @create: 2020-05-10 10:17
 **/
@Mapper
public interface ManagerMapper {
    @Select("select * from manager where id=#{id} and password=#{password}")
    Manager Login(@Param("id") long id,@Param("password") String password);

    //为什么这里要用$呢？因为用#相当于用问好，而用$则是直接替换，而在update语句中，需要更新的字段不能用问号，所以需要用$进行直接替换
    @Update("update manager set ${name}=#{value} where id=#{id}")
    void updateProfile(@Param("name") String name,@Param("value") String value ,@Param("id") Long id);
}
