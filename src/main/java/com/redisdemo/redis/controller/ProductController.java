package com.redisdemo.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redisdemo.redis.entity.Product;
import com.redisdemo.redis.respository.ProductRepository;
import com.redisdemo.redis.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {	

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product save(@RequestBody Product product) {

        return productRepository.save(product);
    }

    @GetMapping("/all")
    @Cacheable(value = "Product")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id",value = "Product")
    public Product findProduct(@PathVariable int id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id",value = "Product")
    public void remove(@PathVariable int id) {
        productRepository.deleteById(id);
    }

}
