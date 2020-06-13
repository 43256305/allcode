package com.example.foodorderingsystem.mapper;

import com.example.foodorderingsystem.pojo.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Results({
            @Result(property = "applicantId",column = "applicant_id"),
            @Result(property = "handleId",column = "handler_id")
    })
    @Select("select id,timestamp,applicant_id,message,handler_id from record")
    List<Record> findAll();

    @Results({
            @Result(property = "applicantId",column = "applicant_id"),
            @Result(property = "handleId",column = "handler_id")
    })
    @Select("select id,timestamp,applicant_id,message,handler_id from record where handler_id=#{handleId}")
    List<Record> find(Long handleId);   //寻找某个管理员处理的记录

    @Insert("insert into record(timestamp,applicant_id,message,handler_id) values (#{record.timestamp},#{record.applicantId}," +
            "#{record.message},#{record.handleId})")
    void insertRecord(@Param("record") Record record);
}
