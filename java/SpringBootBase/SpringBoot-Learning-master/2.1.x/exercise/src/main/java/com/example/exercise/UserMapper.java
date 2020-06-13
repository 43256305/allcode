package com.example.exercise;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "age",column = "age")
    })
    @Select("select id,name,age from USER")
    List<User> getAllUsers();

    @Insert("insert into user(id,name,age) values(#{id},#{name},#{age})")
    void insert(@Param("id") Long id,@Param("name") String name,@Param("age") Integer age);

    @Update("update user set name=#{name},age=#{age} where id=#{id}")
    void updateById(@Param("id") Long id,@Param("name") String name,@Param("age") Integer age);

    @Delete("delete from user where id=#{id}")
    void deleteById(@Param("id") Long id);
}
