package entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pool {

    private Long poolId;
    private String poolName;
    private String poolPin;
    private List<Products> poolItems;

}
