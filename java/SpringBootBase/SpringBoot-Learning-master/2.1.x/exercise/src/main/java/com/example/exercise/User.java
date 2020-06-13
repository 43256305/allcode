package com.example.exercise;

import lombok.Data;

/**
 * @program: exercise
 * @description: 用户实体
 * @author: xjh
 * @create: 2020-03-14 21:08
 **/

@Data
public class User {
    private Long id;
    private String name;
    private int age;
}
