package com.example.greenbookbackend.repos;

import com.example.greenbookbackend.entites.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products,Long> {
    Optional<List<Products>> findByProductName(String productName);
    List<Products> findBySellerUid(String sellerUid);
    List<Products> findByPoolPoolId(Long poolId);

}
