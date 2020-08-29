package com.example.seckill.service;

import com.example.seckill.dao.OrderDao;
import com.example.seckill.model.Order;
import com.example.seckill.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: seckill
 * @description:
 * @author: xjh
 * @create: 2020-07-28 09:42
 **/
@Service
public class OrderService {
    @Autowired
    private ProductService productService;
    @Autowired(required = false)
    private OrderDao orderDao;


    @Transactional
    public void saveOrder(Order order){
        orderDao.saveOrder(order);
    }

    @Transactional
    public void seckill(Long productId){
        Product product = productService.getProductById(productId);

        if (product.getStock() <= 0){
            throw new RuntimeException("商品已售完");
        }

        Order order = new Order();
        order.setProductId(productId);
        order.setAmount(product.getPrice());
        saveOrder(order);

        int updateNum = productService.deductProductStock(productId);  //代表更新了多少行
        if (updateNum <= 0){
            throw new RuntimeException("商品已售完");
        }
    }
}
