package com.example.greenbookbackend.services.Impl;

import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.entites.Seller;
import com.example.greenbookbackend.models.GreenBookApiException;
import com.example.greenbookbackend.repos.ProductsRepository;
import com.example.greenbookbackend.repos.SellerRepository;
import com.example.greenbookbackend.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class

SellerServiceImpl implements SellerService {

    @Autowired
    public SellerRepository sellerRepository;

    @Autowired
    public ProductsRepository productsRepository;
    @Override
    public Seller addSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller addProductToSeller(Long sellerId, Products product) {
        // Find the seller by ID
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new GreenBookApiException(HttpStatus.NOT_FOUND, "Seller not found"));

        // Set the seller on the product
        product.setSeller(seller);

        // Save the product
        Products savedProduct = productsRepository.save(product);

        // Add the product to the seller's product list
        seller.getProductsList().add(savedProduct);

        // Save the seller with the updated product list
        return sellerRepository.save(seller);
    }

    public Optional<Seller> getSellerByUid(String sellerUid) {
        return sellerRepository.findBySellerUid(sellerUid);
    }
    @Override
    public List<Seller> getSellersAll() {
        return sellerRepository.findAll();
    }

    public Long getSellerIdByUid(String sellerUid) {
        Optional<Seller> sellerOptional = sellerRepository.findBySellerUid(sellerUid);
        return sellerOptional.map(Seller::getSellerId).orElse(null);
    }
}
