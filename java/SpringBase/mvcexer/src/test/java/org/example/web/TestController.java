package org.example.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-29 22:10
 **/
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring/spring-web.xml", "classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
// 配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@TransactionConfiguration(transactionManager = "transactionManager" , defaultRollback =  true)
@Transactional
public class TestController {

    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc=webAppContextSetup(this.wac).alwaysExpect(status().isOk()).alwaysDo(print()).build();
    }

    @Test
    public void TestApoint() throws Exception{
        this.mockMvc.perform(post("/appoint").param("couid","1001").param("teaid","17201303").accept(MediaType.APPLICATION_JSON)).andExpect(content().contentType("application/json;charset=utf-8"));
    }

}
