package cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartVO {
    private Long cartId;
    private Long userId;
    private String isbn;
    private String bookName;
    private int price;
    private int quantity;
    private int stock;


}

