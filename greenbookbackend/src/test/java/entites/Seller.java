package entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    private Long sellerId;
    private String sellerName;
    private String mobile;
    private String email;

}
