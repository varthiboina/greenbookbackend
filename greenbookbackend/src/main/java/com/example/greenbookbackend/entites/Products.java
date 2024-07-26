package com.example.greenbookbackend.entites;
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
    private String productName;
    @ManyToOne
    @JoinColumn(name = "seller_seller_id")
    private Seller seller;
    private String quality;
    private String productDescription;
    private String productType;
    private Double price;


}
