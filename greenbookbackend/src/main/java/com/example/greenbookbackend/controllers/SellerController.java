package com.example.greenbookbackend.controllers;

import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.entites.Seller;
import com.example.greenbookbackend.models.ResponseModel;
import com.example.greenbookbackend.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    public SellerService sellerService;



    @PostMapping("/add")
    public ResponseEntity<ResponseModel<Seller>> addSeller(
            @RequestPart("sellerName") String sellerName,
            @RequestPart("email") String sellerEmail ,
            @RequestPart("sellerId") String sellerUid )
    {

        // Create and set the Seller object
        Seller seller = new Seller();
        seller.setSellerName(sellerName);
        seller.setEmail(sellerEmail);
        seller.setSellerUid(sellerUid);

        // Save the seller
        Seller savedSeller = sellerService.addSeller(seller);

        // Return response
        return new ResponseEntity<>(
                new ResponseModel<>(HttpStatus.OK.value(), "Seller saved", savedSeller),
                HttpStatus.OK
        );
    }
    @PostMapping("/{sellerId}/products")
    public ResponseEntity<Seller> addProductToSeller(
            @PathVariable Long sellerId,
            @RequestBody Products product) {

        Seller updatedSeller = sellerService.addProductToSeller(sellerId, product);
        return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Seller> getAll()
    {

        return sellerService.getSellersAll();
    }

    @GetMapping("/uid/{sellerUid}")
    public Seller getSellerByUid(@PathVariable String sellerUid) {
        return sellerService.getSellerByUid(sellerUid)
                .orElseGet(() -> {
                    // Create and return a default Seller object
                    Seller defaultSeller = new Seller();
                    defaultSeller.setSellerUid("default-uid");
                    defaultSeller.setSellerName("Default Seller");
                    defaultSeller.setMobile("000-000-0000");
                    defaultSeller.setEmail("default@example.com");
                    // You can also set other fields or leave them as null/empty
                    return defaultSeller;
                });
    }
    @GetMapping("/sellerIdByUid")
    public Long getSellerIdByUid(@RequestParam String sellerUid) {
        return sellerService.getSellerIdByUid(sellerUid);
    }

}
