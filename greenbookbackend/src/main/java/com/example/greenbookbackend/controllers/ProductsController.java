package com.example.greenbookbackend.controllers;

import com.example.greenbookbackend.entites.Image;
import com.example.greenbookbackend.entites.Pool;
import com.example.greenbookbackend.entites.Products;
import com.example.greenbookbackend.entites.Seller;
import com.example.greenbookbackend.models.ResponseModel;
import com.example.greenbookbackend.services.PoolService;
import com.example.greenbookbackend.services.ProductService;
import com.example.greenbookbackend.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductsController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SellerService sellerService;

    @Autowired
    private PoolService poolService;

    @PostMapping("/add")
    public ResponseModel<Products> addProduct(
            @RequestParam("productName") String productName,
            @RequestParam("sellerId") String sellerId,
            @RequestParam("poolName") String poolName,
            @RequestParam("quality") String quality,
            @RequestParam("productDescription") String productDescription,
        //    @RequestParam("productType") String productType,
            @RequestParam("price") Double price,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        // Create Product entity
        Optional<Seller> seller = sellerService.getSellerByUid(sellerId);

        Seller seller_ = seller.get();
        Pool pool = poolService.getPoolByName(poolName);

        Products product = new Products();
        product.setSellerUid(sellerId);
        product.setProductName(productName);
        product.setQuality(quality);
        product.setProductDescription(productDescription);
     //   product.setProductType(productType);
        product.setPrice(price);
        product.setSeller(seller_);
        product.setPool(pool);

        Image image = null;
        if (file != null && !file.isEmpty()) {
            // Create Image entity from MultipartFile if file is provided
            image = new Image();
            image.setName(file.getOriginalFilename());
            image.setType(file.getContentType());
            image.setData(file.getBytes());
        }

        // Save product and image (if any)
        final Products savedProduct = productService.saveProduct(product, image);
        return new ResponseModel<>(HttpStatus.OK.value(), "Product saved", savedProduct);
    }

    @GetMapping("/seller/{sellerUid}")
    public ResponseEntity<List<Products>> getProductsBySellerId(@PathVariable String sellerUid) {
        //Long sellerId = sellerService.getSellerIdByUid(sellerUid);
        List<Products> products = productService.getProductsBySellerId(sellerUid);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/pool/{poolName}")
    public ResponseEntity<List<Products>> getProductsByPoolName(@PathVariable String poolName) {
        //Long sellerId = sellerService.getSellerIdByUid(sellerUid);
        List<Products> products = productService.getProductsByPoolName(poolName);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/pool/poolId/{poolId}")
    public ResponseEntity<List<Products>> getProductsByPoolId(@PathVariable Long poolId) {
        //Long sellerId = sellerService.getSellerIdByUid(sellerUid);
        List<Products> products = productService.findByPoolPoolId(poolId);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/all")
    public List<Products> getAll() {
        return productService.getProductsAll();
    }

    @GetMapping("/{productName}")
    public ResponseEntity<List<Products>> getProductByName(@PathVariable(name = "productName") String productName) {
        return ResponseEntity.ok(productService.getProductsByName(productName));
    }
    private String generateDefaultSellerId() {
        // Logic to generate a default or placeholder seller ID if needed
        return "defaultSellerId"; // Replace with actual logic or remove if not needed
    }
}
