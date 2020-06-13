package com.didispace.chapter21;

import lombok.Data;

//引入lombok包是为了消除一些程式化代码如setter，getter，判断空指针
//需要安装lombok插件，再在pom.xml中引入jar包
@Data
public class User {

    private Long id;
    private String name;
    private Integer age;

}
