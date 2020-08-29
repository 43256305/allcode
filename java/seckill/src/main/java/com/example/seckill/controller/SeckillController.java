package com.example.seckill.controller;

import com.example.seckill.common.Constants;
import com.example.seckill.model.Product;
import com.example.seckill.service.OrderService;
import com.example.seckill.service.ProductService;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @program: seckill
 * @description:
 * @author: xjh
 * @create: 2020-07-28 09:59
 **/
@RestController   //当用Controller注释时，会报错，匹配不到view，因为seckill返回void，不是一个视图，所以要用RestController
public class SeckillController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ZooKeeper zooKeeper;

    private static final Logger logger = LoggerFactory.getLogger(SeckillController.class);

    private static ConcurrentHashMap<Long, Boolean> productSoldOutMap = new ConcurrentHashMap<>();

    public static ConcurrentHashMap getProductSoldOutMap(){
        return productSoldOutMap;
    }

    //@PostConstruct和@PreDestroy两个作用于Servlet生命周期的注解，实现Bean初始化之前和销毁之前的自定义操作
    //在项目中主要是在Servlet初始化之前加载一些缓存数据等
    @PostConstruct
    public void init(){
        List<Product> products = productService.listProducts();
        for (Product p:products){
            //常量为 product_stock_ 连上商品id后形成一个唯一标识符放在redis中      Integer变为String只需要加上空字符串即可（自动拆箱）
            stringRedisTemplate.opsForValue().set(Constants.REDIS_PRODUCT_STOCK_PREFIX + p.getId(), p.getStock() + "");
        }
    }


    @PostMapping("/{productId}")
    public void seckill(@PathVariable("productId") Long productId) throws KeeperException, InterruptedException {
        if (productSoldOutMap.get(productId) != null){
            logger.error("商品已售完",new Exception("商品已售完"));
            return;
        }
        //decr()为原子减操作   原子操作加数据层面的update乐观锁可以防止超卖
        Long stock = stringRedisTemplate.opsForValue().decrement(Constants.REDIS_PRODUCT_STOCK_PREFIX+productId);

        if (stock < 0){
            stringRedisTemplate.opsForValue().increment(Constants.REDIS_PRODUCT_STOCK_PREFIX + productId);
            productSoldOutMap.put(productId, true);
            String zkPath = Constants.getZkPRODUCTSoldOutPath(productId);
            if (zooKeeper.exists(zkPath,true)==null){
                zooKeeper.create(zkPath, "true".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            //监听zk售完节点是否为true
            zooKeeper.exists(zkPath,true);
            logger.error("商品已售完",new Exception("商品已售完"));
            return;
        }

        try{
            orderService.seckill(productId);
        }catch (Exception e){
            stringRedisTemplate.opsForValue().increment(Constants.REDIS_PRODUCT_STOCK_PREFIX + productId);
            if (productSoldOutMap.get(productId) != null){
                productSoldOutMap.remove(productId);
            }
            //一旦发生异常，则把zkPath路劲设为false，应用会自动回调zookeeper中的process方法，把内存缓存去除
            String zkPath = Constants.getZkPRODUCTSoldOutPath(productId);
            if (zooKeeper.exists(zkPath, true) != null){
                zooKeeper.setData(zkPath, "false".getBytes(),-1);
            }
            logger.error("创建订单失败",e);
        }
    }

    @GetMapping("/")
    @ResponseBody
    public String test(){
        return "ceshi";
    }
}
