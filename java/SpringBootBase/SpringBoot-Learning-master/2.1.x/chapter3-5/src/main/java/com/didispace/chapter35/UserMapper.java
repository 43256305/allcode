package com.didispace.chapter35;

import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    //select是查询类的注解，所有查询均使用这个注解
    //#与$的区别： name=#{name}相当于name=?  name=${name}相当于name=somename
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    //而对于“查”操作，我们往往需要进行多表关联，汇总计算等操作，那么对于查询的结果往往就不再是简单的实体对象了，
    // 往往需要返回一个与数据库实体不同的包装类，那么对于这类情况，就可以通过@Results和@Result注解来进行绑定
    //property对应实体中的名字，column对应数据库中存储的名字
    //下面返回的实体类中，id为null
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT name, age FROM user")
    List<User> findAll();

}
