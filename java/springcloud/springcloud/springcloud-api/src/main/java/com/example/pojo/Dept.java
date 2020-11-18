package com.example.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: springcloud
 * @description:
 * @author: xjh
 * @create: 2020-11-16 10:47
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long deptno;
    private String deptname;
    private String db_source;

    public Dept(String deptname){
        this.deptname = deptname;
    }
}
