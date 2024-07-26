package com.example.greenbookbackend.controllers;

import com.example.greenbookbackend.entites.Seller;
import com.example.greenbookbackend.models.ResponseModel;
import com.example.greenbookbackend.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    public SellerService sellerService;

    @PostMapping("/add")
    public ResponseModel<Seller> addSeller(@RequestBody Seller seller)
    {
        final Seller savedSeller = sellerService.addSeller(seller);
        return new ResponseModel<>(HttpStatus.OK.value(),"Bus saved" , savedSeller);
    }

    @GetMapping("/all")
    public List<Seller> getAll()
    {

        return sellerService.getSellersAll();
    }
}
