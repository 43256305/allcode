package com.didispace.chapter41;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @program: webassignment2
 * @description: studentbean
 * @author: xjh
 * @create: 2020-03-11 10:51
 **/

//不需要让spring自动生成的类（我们是在factory中手动生成），不需要设@Component，要不然会报错
@Data
@AllArgsConstructor
public class Student {
    private String name;
    private long id;
    private int age;

}
