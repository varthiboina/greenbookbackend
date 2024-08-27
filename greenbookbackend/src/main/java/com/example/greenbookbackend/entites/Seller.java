package com.example.greenbookbackend.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @Column(nullable = false, unique = true)
    private String sellerUid;

    @Column(nullable = false)
    private String sellerName;

    private String mobile;
    private String email;

    @OneToMany(mappedBy = "seller")
    @JsonManagedReference  // Prevents infinite recursion during serialization
    private List<Products> productsList;
}
