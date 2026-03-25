package order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemVO {
	private Long orderItemId;
	private Long orderId;
	private String isbn;
	private int quantity;
	private int orderPrice;
	private String bookTitle;
	private String bookImage;
}
