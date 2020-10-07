package com.example.seckill.zookeeper;

import com.example.seckill.common.Constants;
import com.example.seckill.controller.SeckillController;
import lombok.Data;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

/**
 * @program: seckill
 * @description:
 * @author: xjh
 * @create: 2020-08-15 21:37
 **/
@Service
@Data
public class ZookeeperWatcher implements Watcher {

    private ZooKeeper zooKeeper;

    //监听回调方法，一旦controller中监听的路劲不为true了，则会调用此方法
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("get notification.");

        //当前没有节点  创建节点
        if (watchedEvent.getType() == Event.EventType.None && watchedEvent.getPath() == null) {
            System.out.println("connect successfully.");

            try {
                // 创建 zookeeper 商品售罄信息根节点
                String path = Constants.ZK_PRODUCT_SOLD_OUT_FLAG;
                if (zooKeeper != null && zooKeeper.exists(path, false) == null) {
                    zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }

        } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {   //有节点 数据改变则改变更改内存缓存
            try {
                // 获取节点路径
                String path = watchedEvent.getPath();
                // 获取节点数据
                String soldOut = new String(zooKeeper.getData(path, true, new Stat()));
                // 处理当前服务器对应 JVM 缓存
                if ("false".equals(soldOut)) {
                    // 获取商品 Id
                    String productId = path.substring(path.lastIndexOf("/") + 1, path.length());
                    System.out.println("productId：" + productId);
                    // 同步当前 JVM 缓存
                    if (SeckillController.getProductSoldOutMap().contains(productId)) {
                        SeckillController.getProductSoldOutMap().remove(productId);
                    }
                }

            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
