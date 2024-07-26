package com.example.greenbookbackend.controllers;

import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.entites.Seller;
import com.example.greenbookbackend.models.ResponseModel;
import com.example.greenbookbackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductsController {

    @Autowired
    public ProductService productService;

    @PostMapping("/add")
    public ResponseModel<Products> addSeller(@RequestBody Products products)
    {
        final Products savedProducts = productService.addProduct(products);
        return new ResponseModel<>(HttpStatus.OK.value(),"Bus saved" , savedProducts);
    }

    @GetMapping("/all")
    public List<Products> getAll()
    {

        return productService.getProductsAll();
    }

    @GetMapping("/{productName}")
    public ResponseEntity<List<Products>> getProductByName(@PathVariable(name = "productName") String productName)
    {
        return ResponseEntity.ok(productService.getProductsByName(productName));
    }


}
