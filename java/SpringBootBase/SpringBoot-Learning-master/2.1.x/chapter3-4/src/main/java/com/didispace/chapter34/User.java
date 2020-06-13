package com.didispace.chapter34;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//由于配置了hibernate.hbm2ddl.auto，在应用启动的时候框架会自动去数据库中创建对应的表
//Entity表明User是一个持久化的实体
@Entity
@Data
@NoArgsConstructor
public class User {

    //@Id和@GeneratedValue用来标识User对应对应数据库表中的主键
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}