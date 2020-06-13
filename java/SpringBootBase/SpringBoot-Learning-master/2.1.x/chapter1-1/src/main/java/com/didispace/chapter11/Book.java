package com.didispace.chapter11;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: chapter1-1
 * @description: 书籍
 * @author: xjh
 * @create: 2020-03-11 17:50
 **/
@Component
public class Book {
    @Value("${book.name}")
    private String name;
    @Value("${book.author}")
    private String author;
    @Value("${book.desc}")
    private String desc;

    public String getName(){
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
