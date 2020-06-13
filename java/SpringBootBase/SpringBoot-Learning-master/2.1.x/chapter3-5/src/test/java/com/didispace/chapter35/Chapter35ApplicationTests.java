package com.didispace.chapter35;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class Chapter35ApplicationTests {

    @Resource
    //或者使用@Autowired(required=false)，默认时此注解要求依赖必须存在，如果按要求按名称装配，则要配合@Qualifier("baseDao")使用
    private UserMapper userMapper;//用@Autowired（按类型装配）时，虽然报错，但还是能使用，而@Resource可以即不报错，又正确使用
    //@Resource可以ByName（默认）(id匹配的bean)或者ByType（class匹配的bean）自动注入，没有找到或者找到多个都会异常
    //当默认时，name没有找到就会找type，当明确@Resource(name="baseDao")时，就只会查找名称
    //两个注解都可以写在类上或者setter上
    //推荐resource

    @Test
    @Rollback
    public void test() throws Exception {
        // insert一条数据，并select出来验证
        userMapper.insert("AAA", 20);
        User u = userMapper.findByName("AAA");
        Assert.assertEquals(20, u.getAge().intValue());
        // update一条数据，并select出来验证
        u.setAge(30);
        userMapper.update(u);
        u = userMapper.findByName("AAA");
        Assert.assertEquals(30, u.getAge().intValue());
        // 删除这条数据，并select验证
        userMapper.delete(u.getId());
        u = userMapper.findByName("AAA");
        Assert.assertEquals(null, u);
    }

}
