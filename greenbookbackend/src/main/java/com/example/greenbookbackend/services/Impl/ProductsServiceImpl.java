package com.example.greenbookbackend.services.Impl;

import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.models.GreenBookApiException;
import com.example.greenbookbackend.repos.ProductsRepository;
import com.example.greenbookbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductService {

    @Autowired
    public ProductsRepository productsRepository;

    @Override
    public Products addProduct(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public List<Products> getProductsAll() {
        return productsRepository.findAll();
    }

    @Override
    public List<Products> getProductsByName(String productName) {
        return productsRepository.findByProductName(productName).orElseThrow(() -> new GreenBookApiException(HttpStatus.BAD_REQUEST,"No such route found"));
    }



}
