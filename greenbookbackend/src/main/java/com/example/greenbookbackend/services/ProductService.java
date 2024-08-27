package com.example.greenbookbackend.services;

import com.example.greenbookbackend.entites.Image;
import com.example.greenbookbackend.entites.Products;

import java.util.List;

public interface ProductService {

    Products addProduct(Products products);
    List<Products> getProductsAll();

    List<Products> getProductsByName(String productName);

    Products saveProduct(Products product, Image image);
    public List<Products> getProductsBySellerId(String sellerUid);
    List<Products> findByPoolPoolId(Long poolId);

    List<Products> getProductsByPoolName(String poolName);
}
