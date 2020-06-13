package org.example.service;

import org.example.BaseTest;
import org.example.dto.AppointMent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-29 21:49
 **/
public class TestService extends BaseTest {
    @Autowired
    private TeachCourse teachCourse;

    @Test
    public void testTeachCouse(){
        AppointMent a=teachCourse.teachCouse(17201303,1001);
        System.out.println();
    }
}
