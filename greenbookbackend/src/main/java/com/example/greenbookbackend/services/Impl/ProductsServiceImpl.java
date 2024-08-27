package com.example.greenbookbackend.services.Impl;

import com.example.greenbookbackend.entites.Image;
import com.example.greenbookbackend.entites.Pool;
import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.models.GreenBookApiException;
import com.example.greenbookbackend.repos.ImageRepository;
import com.example.greenbookbackend.repos.ProductsRepository;
import com.example.greenbookbackend.services.PoolService;
import com.example.greenbookbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PoolService poolService;

    @Override
    public Products addProduct(Products products) {
        // This method may not be used if saveProduct is used
        return productsRepository.save(products);
    }

    @Override
    public List<Products> getProductsAll() {
        return productsRepository.findAll();
    }

    @Override
    public List<Products> getProductsByName(String productName) {
        return productsRepository.findByProductName(productName)
                .orElseThrow(() -> new GreenBookApiException(HttpStatus.BAD_REQUEST, "No such product found"));
    }


    @Override
    public Products saveProduct(Products product, Image image) {
        if (image != null) {
            image = imageRepository.save(image); // Save the image first
            product.setImage(image); // Associate the image with the product
        }
        return productsRepository.save(product); // Save the product
    }



    public List<Products> getProductsBySellerId(String sellerUid) {
        return productsRepository.findBySellerUid(sellerUid);
    }



    public List<Products> findByPoolPoolId(Long poolId)
   {
       return productsRepository.findByPoolPoolId(poolId);
   }

    @Override
    public List<Products> getProductsByPoolName(String poolName) {
        Pool pool = poolService.getPoolByName(poolName);
        Long poolId = pool.getPoolId();
        return findByPoolPoolId(poolId);
    }


}
