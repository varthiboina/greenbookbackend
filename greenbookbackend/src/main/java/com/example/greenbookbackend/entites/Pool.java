// Pool.java
package com.example.greenbookbackend.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "pools")
public class Pool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poolId;
    private String poolName;
    private String poolPin;
    private String poolDescription;

    @ManyToMany
    @JoinTable(
            name = "pool_sellers",
            joinColumns = @JoinColumn(name = "pool_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "seller_id", referencedColumnName = "sellerId"),
                    @JoinColumn(name = "seller_uid", referencedColumnName = "sellerUid")
            }
    )
    @JsonIgnore  // Prevents infinite recursion during serialization
    private List<Seller> sellerList;

    // One pool can have many products, but each product belongs to only one pool
    @OneToMany(mappedBy = "pool")
    @JsonIgnore  // Prevents infinite recursion during serialization
    private List<Products> productList;
}
