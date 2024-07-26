package com.example.greenbookbackend.services;


import com.example.greenbookbackend.entites.Seller;

import java.util.List;

public interface SellerService {

    Seller addSeller(Seller seller);
    List<Seller> getSellersAll();

}
