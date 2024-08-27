package com.example.greenbookbackend.repos;

import com.example.greenbookbackend.entites.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    Optional<Seller> findBySellerUid(String sellerUid);

}
