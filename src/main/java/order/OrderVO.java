package order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
	private int orderId;
	private String memberId;
	private String isbn;
	private int orderPrice;
	private int quantity;
	private String status;
	private String deliveryAddress;
}
