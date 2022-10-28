package com.redisdemo.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redisdemo.redis.entity.Product;
import com.redisdemo.redis.respository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product findById(int id) {
        System.out.println("fetched from db");
        return productRepository.findById(id).get();
    }
}
