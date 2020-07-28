package com.example.seckill.service;

import com.example.seckill.model.Order;
import com.example.seckill.model.Product;
import org.mockito.internal.matchers.Or;
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
    @Autowired
    private OrderService orderService;


    @Transactional
    public void saveOrder(Order order){
        orderService.saveOrder(order);
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

        int updateNum = productService.deductProductStock(productId);
        if (updateNum <= 0){
            throw new RuntimeException("商品已售完");
        }
    }
}
