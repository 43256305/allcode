package com.example.foodorderingsystem.mapper;

import com.example.foodorderingsystem.pojo.User;
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
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User searchUser(Long id);

    //修改user的角色
    @Update("update user set iden=#{iden} where id=#{id}")
    void updateRole(Long id,int iden);
}
