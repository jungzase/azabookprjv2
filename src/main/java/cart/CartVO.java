package cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartVO {
	private int cartId;
	private String memberId;
	private String isbn;
	private int quantity;
}
