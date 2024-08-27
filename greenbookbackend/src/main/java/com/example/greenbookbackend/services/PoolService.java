package com.example.greenbookbackend.services;

import com.example.greenbookbackend.entites.Pool;
import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.entites.Seller;
import java.util.List;

public interface PoolService {
    Pool addPool(Pool pool);
    List<Pool> getAllPools();
    Pool getPoolById(Long poolId);

    Pool getPoolByName(String poolName);
    void deletePool(Long poolId);
    Pool addSellerToPool(String poolName, Seller seller);

    List<Products> getAllProductsInPool(Long poolId);
    List<Products> getAllProductsInPoolByName(String poolName);
    public boolean checkPoolExistence(String poolName, String poolPin);
}
