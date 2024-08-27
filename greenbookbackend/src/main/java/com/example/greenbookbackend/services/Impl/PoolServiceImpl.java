package com.example.greenbookbackend.services.Impl;

import com.example.greenbookbackend.entites.Pool;
import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.entites.Seller;
import com.example.greenbookbackend.repos.PoolRepository;
import com.example.greenbookbackend.services.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolRepository poolRepository;

    @Override
    public Pool addPool(Pool pool) {
        return poolRepository.save(pool);
    }

    @Override
    public List<Pool> getAllPools() {
        return poolRepository.findAll();
    }

    @Override
    public Pool getPoolById(Long poolId) {
        Optional<Pool> pool = poolRepository.findById(poolId);
        return pool.orElse(null);
    }


    @Override
    public Pool getPoolByName(String poolName) {
        Optional<Pool> pool = poolRepository.findByPoolName(poolName);
        return pool.orElse(null);
    }

    public boolean checkPoolExistence(String poolName, String poolPin) {
        return poolRepository.existsByPoolNameAndPoolPin(poolName, poolPin);
    }


    public List<Products> getAllProductsInPoolByName(String poolName) {
        Pool pool = getPoolByName(poolName);
        if (pool == null) {
            return new ArrayList<>(); // Return an empty list if pool is not found
        }

        List<Products> allProducts = new ArrayList<>();
        for (Seller seller : pool.getSellerList()) {
            List<Products> sellerProducts = seller.getProductsList();
            if (sellerProducts != null) {
                allProducts.addAll(sellerProducts);
            }
        }

        return allProducts;
    }

    @Override
    public void deletePool(Long poolId) {
        poolRepository.deleteById(poolId);
    }
    @Override
    public Pool addSellerToPool(String poolName, Seller seller) {

        Optional<Pool> optionalPool = poolRepository.findByPoolName(poolName);
        if (optionalPool.isPresent()) {
            Pool pool = optionalPool.get();
            if (pool.getSellerList() != null) {
                pool.getSellerList().add(seller);
            } else {
                pool.setSellerList(List.of(seller));
            }
            return poolRepository.save(pool);
        }
        return null;
    }

    @Override
    public List<Products> getAllProductsInPool(Long poolId) {
        return null;
    }
}
