package com.example.foodorderingsystem.mapper;

import com.example.foodorderingsystem.pojo.Application;
import com.example.foodorderingsystem.pojo.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApplicationMapper {
    @Results({
            @Result(property = "applicantId",column = "user_id")
    })
    @Select({"select id,user_id,iden,toIden from application"})
    List<Application> findAll();

    @Results({
            @Result(property = "applicantId",column = "user_id")
    })
    @Select("select id,user_id,iden,toIden from application where id=#{id}")
    Application findApplication(long id);

    @Delete("delete from application where id=#{id}")
    void deleteApplication(long id);

}
