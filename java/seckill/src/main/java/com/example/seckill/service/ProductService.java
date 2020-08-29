package com.example.seckill.service;

import com.example.seckill.dao.ProductDao;
import com.example.seckill.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Product getProductById(Long id){
        return productDao.getProductById(id);
    }

    @Transactional
    public List<Product> listProducts(){return productDao.listProducts();}

    @Transactional
    public int deductProductStock(Long id){
        return productDao.deductProductStock(id);
    }

    @Transactional
    public void saveProduct(Product product){
        productDao.saveProduct(product);
    }

    @Transactional
    public void deleteProduct(Long id){
        productDao.deleteProduct(id);
    }

    @Transactional
    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }


}
