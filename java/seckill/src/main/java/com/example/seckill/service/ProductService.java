package com.example.seckill.service;

import com.example.seckill.dao.ProductDao;
import com.example.seckill.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: seckill
 * @description:
 * @author: xjh
 * @create: 2020-07-28 09:43
 **/
@Service
public class ProductService {
    @Autowired(required = false)
    private ProductDao productDao;

    @Transactional
    public Product getProductById(Long productId){
        return null;
    }

    @Transactional
    public int deductProductStock(Long productId){
        return 0;
    }
}
