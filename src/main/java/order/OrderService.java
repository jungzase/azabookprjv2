package order;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderVO order);
    Long createDirectOrder(OrderVO order, String isbn, int quantity);
    List<OrderVO> getOrderListByUser(Long userId);
    List<OrderVO> getAllOrders();
    boolean updateStatus(Long orderId, int status);
}


