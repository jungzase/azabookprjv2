package order;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
	private Long orderId;
	private Long userId;
	private String orderDate;
	private int orderStatus;
	private int totalPrice;
	private String receiverName;
	private String receiverHp;
	private String deliveryAddress;
	private String deliveryDetailAddress;
	private List<OrderItemVO> items = new ArrayList<OrderItemVO>();
	private String username;

	public String getStatusLabel() {
		switch (orderStatus) {
		case 0:
			return "주문완료";
		case 1:
			return "상품준비중";
		case 2:
			return "배송중";
		case 3:
			return "배송완료";
		case 4:
			return "주문취소";
		default:
			return "미확인";
		}
	}

	public OrderItemVO getFirstItem() {
		if (items == null || items.isEmpty()) {
			return null;
		}
		return items.get(0);
	}
}
