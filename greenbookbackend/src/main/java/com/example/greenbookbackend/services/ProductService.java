package com.example.greenbookbackend.services;

import com.example.greenbookbackend.entites.Products;

import java.util.List;

public interface ProductService {

    Products addProduct(Products products);
    List<Products> getProductsAll();

    List<Products> getProductsByName(String productName);

}
