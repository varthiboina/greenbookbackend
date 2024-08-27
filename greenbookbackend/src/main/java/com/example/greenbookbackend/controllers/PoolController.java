package com.example.greenbookbackend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.greenbookbackend.entites.Pool;
import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.entites.Seller;
import com.example.greenbookbackend.services.PoolService;
import com.example.greenbookbackend.services.ProductService;
import com.example.greenbookbackend.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pools")
public class PoolController {

    private static final Logger logger = LoggerFactory.getLogger(PoolController.class);

    @Autowired
    private PoolService poolService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity<Pool> createPool(
            @RequestParam("poolName") String poolName,
            @RequestParam("poolPin") String poolPin,
            @RequestParam("poolDescription") String poolDescription) {

        Pool pool = new Pool();
        pool.setPoolName(poolName);
        pool.setPoolPin(poolPin);
        pool.setPoolDescription(poolDescription);

        Pool newPool = poolService.addPool(pool);
        return new ResponseEntity<>(newPool, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pool>> getAllPools() {
        List<Pool> pools = poolService.getAllPools();
        return new ResponseEntity<>(pools, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkPoolExistence(
            @RequestParam String poolName,
            @RequestParam String poolPin) {

        boolean exists = poolService.checkPoolExistence(poolName, poolPin);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/{poolId}")
    public ResponseEntity<Pool> getPoolById(@PathVariable Long poolId) {
        Pool pool = poolService.getPoolById(poolId);
        if (pool != null) {
            return new ResponseEntity<>(pool, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{poolName}/getallproducts")
    public ResponseEntity<List<Products>> getAllProductsInPoolByName(@PathVariable String poolName) {
        List<Products> products = poolService.getAllProductsInPoolByName(poolName);

        // Return an empty list with HttpStatus.OK if no products are found
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{poolId}")
    public ResponseEntity<Void> deletePool(@PathVariable Long poolId) {
        poolService.deletePool(poolId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PostMapping("/{poolName}/sellers")
    public ResponseEntity<Pool> addSellerToPool(
            @PathVariable String poolName,
            @RequestBody Seller seller) {

        logger.info("Received request to add seller to pool. Pool Name: {}", poolName);
        logger.info("Seller details: {}", seller);

        Optional<Seller> existingSellerOptional = sellerService.getSellerByUid(seller.getSellerUid());

        // Log if an existing seller was found
        if (existingSellerOptional.isPresent()) {
            logger.info("Seller already exists with UID: {}", seller.getSellerUid());
        } else {
            logger.info("Seller not found. Adding new seller with UID: {}", seller.getSellerUid());
        }

        // Convert Optional<Seller> to Seller
        Seller existingSeller = existingSellerOptional.orElseGet(() -> {
            logger.info("Adding new seller with UID: {}", seller.getSellerUid());
            return sellerService.addSeller(seller);
        });

        // Now associate the seller with the pool
        Pool updatedPool = poolService.addSellerToPool(poolName, existingSeller);

        // Log the result of adding the seller to the pool
        if (updatedPool != null) {
            logger.info("Seller successfully added to the pool. Pool ID: {}", updatedPool.getPoolId());
            return new ResponseEntity<>(updatedPool, HttpStatus.OK);
        } else {
            logger.error("Failed to add seller to pool. Pool not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
