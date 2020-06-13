package com.neo;

import com.neo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    //redis中key一定为String类型  Redis使用对象来表示数据库中的键和值。每次我们在Redis数据库中新创建一个键值对时，
    // 至少会创建出两个对象（redisObject）。一个是键对象，一个是值对象。

    //如果key是String，value也是String，则用StringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //如果key为String，value是Object(Hash list set sortedset)，则用RedisTemplate
	@Autowired
    private RedisTemplate redisTemplate;


	//https://blog.csdn.net/sinat_27629035/article/details/102652185   redisTemplate常用api
    @Test
    public void test() throws Exception {
        //设置值
        stringRedisTemplate.opsForValue().set("aaa", "111");
        //opsForValue()是操作字符串  opsForHash()  List Set ZSet
        //获取值
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
    
    @Test
    public void testObj() throws Exception {
        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        //为什么这里user能存储呢？因为user被序列化了
        operations.set("com.neox", user);
        //设置生存时间
        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //删除数据  这里用redisTemplate删除的是存储在redis中的键值对，下面hash中用operations删除的是存储在redsi
        //中的hash表中的键值对，redis中的hash表还在
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
        	System.out.println("exists is true");
        }else{
        	System.out.println("exists is false");
        }
       // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }

    @Test
    public void testList() throws Exception{
        //list为双向链表
        ListOperations operations=redisTemplate.opsForList();
        //忘list的左边与右边插入元素
        operations.leftPush("testlist","1");
        operations.rightPush("testlist","2");
        //取出index为1的元素
        System.out.println(operations.index("testlist",1));  //：2
        for (int i=0;i<2;i++){
            //往list左边出队并删除元素
            System.out.println(operations.leftPop("testlist"));  //: 1 2
        }
    }

    @Test
    public void testHash()throws Exception{
        HashOperations operations=redisTemplate.opsForHash();
        //插入元素
        operations.put("testhash","1","first element");
        operations.put("testhash","2","second element");
        //获取元素
        System.out.println(operations.get("testhash","1"));
        //删除元素
        operations.delete("testhash","1");
    }

    @Test
    public void testSet()throws Exception{
        SetOperations operations=redisTemplate.opsForSet();
        //添加元素
        operations.add("testset","1","2","3");
        //获取大小
        System.out.println(operations.size("testset"));  //:3
        //获取set所有元素
        System.out.println(operations.members("testset"));  //[1,2,3]
    }

    @Test
    public void testSortedSet() throws Exception{
        ZSetOperations operations=redisTemplate.opsForZSet();
        //添加元素并指定score（因为要排序）
        operations.add("testZSet","a",2);
        operations.add("testZSet","b",1);
        //根据score范围获取元素
        System.out.println(operations.rangeByScore("testZSet",1,2)); //:[b,a]
    }

    //redis分布式锁
}