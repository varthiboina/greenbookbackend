package entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.Quality;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private Long productId;
    private String productName;
    private Seller seller;
    private Quality quality;
    private String productDescription;
    private String productType;
    private Double price;


}
