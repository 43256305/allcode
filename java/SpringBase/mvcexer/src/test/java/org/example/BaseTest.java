package org.example;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-27 17:03
 **/
@RunWith(SpringJUnit4ClassRunner.class)   //让测试运行于spring环境
//@RunWith(JUnit4.class)就是指用JUnit4来运行
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
//ContextConfiguration  Spring整合JUnit4测试时，使用注解引入多个配置文件
public class BaseTest {

}
