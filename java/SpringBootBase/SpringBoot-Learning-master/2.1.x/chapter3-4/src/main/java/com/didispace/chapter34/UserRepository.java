package com.didispace.chapter34;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//JPA是一个基于O/R映射的标准规范，中文名Java持久层API，是JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中
//JpaRepository接口本身已经实现了创建（save）、更新（save）、删除（delete）、查询（findAll、findOne）等基本操作的函数，因此对于这些基础操作的数据访问就不需要开发者再自己定义。
public interface UserRepository extends JpaRepository<User, Long> {

    //按name查询User实体    jpa可以通过解析方法名创建查询。
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    //如果不使用通过解析方法名来创建查询的话，可以通过使用@Query注解来创建查询
    //您只需要编写JPQL语句，并通过类似“:name”来映射@Param指定的参数
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);

}
