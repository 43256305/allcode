package com.didispace.chapter31;

import lombok.Data;

/**
 * @program: chapter3-1
 * @description:
 * @author: xjh
 * @create: 2020-04-03 11:37
 **/
@Data
public class StudentExecption extends RuntimeException {
    private String message;
    private int code;
}
