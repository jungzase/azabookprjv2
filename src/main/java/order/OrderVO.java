package order;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
    private Long orderId;
    private Long userId;
    private String username;
    private Timestamp orderDate;
    private int orderStatus;
    private int totalPrice;
    private String receiverName;
    private String receiverHp;
    private String deliveryAddress;
    private String deliveryDetailAddress;
    private List<OrderItemVO> items;


}

