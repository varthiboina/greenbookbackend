package com.example.greenbookbackend.services.Impl;

import com.example.greenbookbackend.entites.Seller;
import com.example.greenbookbackend.repos.SellerRepository;
import com.example.greenbookbackend.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    public SellerRepository sellerRepository;
    @Override
    public Seller addSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public List<Seller> getSellersAll() {
        return sellerRepository.findAll();
    }
}
