package com.didispace.chapter35;

import lombok.Data;
import lombok.NoArgsConstructor;

//在mybatis中，实体类需要在数据库中手动创建
@Data
@NoArgsConstructor
public class User {

    private Long id;

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}