package com.example.greenbookbackend.services;


import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.entites.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {

     Seller addSeller(Seller seller);
    Seller addProductToSeller(Long sellerId, Products product);
    List<Seller> getSellersAll();
     Optional<Seller> getSellerByUid(String sellerUid);
     Long getSellerIdByUid(String sellerUid);

}
