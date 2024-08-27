package com.example.greenbookbackend.entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String sellerUid;  // If you are using sellerId to link, ensure this field is handled correctly

    private String productName;
    private String quality;
    private String productDescription;
    private String productType;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "seller_id")  // This should match the field name in Seller entity
    @JsonBackReference  // Prevents infinite recursion during serialization
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "pool_id")
    @JsonBackReference  // Prevents infinite recursion during serialization
    private Pool pool;
}
