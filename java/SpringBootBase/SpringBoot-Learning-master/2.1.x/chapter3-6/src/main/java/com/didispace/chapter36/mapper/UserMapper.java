package com.didispace.chapter36.mapper;

import com.didispace.chapter36.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

//主程序中扫描了mapper包，所以这里就不用注解了@Mapper
public interface UserMapper {

    User findByName(@Param("name") String name);

    int insert(@Param("name") String name, @Param("age") Integer age);

}
