package org.example;

import redis.clients.jedis.Jedis;

/**
 * @program: jedis
 * @description:
 * @author: xjh
 * @create: 2020-05-06 16:22
 **/
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis=new Jedis();
        for (int i = 0; i < 100000; i++) {
            jedis.pfadd("codehole", "user" + i);
            for (int j=0;j<5;j++){
                jedis.pfadd("codehole", "user" + i);
            }
        }
        long total = jedis.pfcount("codehole");
        System.out.printf("%d %d\n", 100000, total);
        jedis.close();
    }
}
