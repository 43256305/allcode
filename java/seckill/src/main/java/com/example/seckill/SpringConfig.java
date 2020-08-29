package com.example.seckill;

import com.example.seckill.zookeeper.ZookeeperWatcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: seckill
 * @description:
 * @author: xjh
 * @create: 2020-07-28 09:21
 **/
@Configuration
public class SpringConfig {

    //服务器zookeeper地址
    private static final String serverZookeeperAddress = "121.199.71.106:2181";

    //配置zookeeper
    @Bean
    public ZooKeeper initZookeeper() throws Exception{
        //创建观察者
        ZookeeperWatcher watcher = new ZookeeperWatcher();
        //创建zookeeper客户端
        ZooKeeper zooKeeper = new ZooKeeper(serverZookeeperAddress, 30000, watcher);
        //将客户端注册给观察者
        watcher.setZooKeeper(zooKeeper);

        return zooKeeper;
    }
}
