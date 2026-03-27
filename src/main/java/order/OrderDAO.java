package order;

import java.util.List;

public interface OrderDAO {
    Long insertOrder(OrderVO order);
    int insertOrderItem(OrderItemVO item);
    List<OrderVO> findByUserId(Long userId);
    List<OrderVO> findAll();
    List<OrderItemVO> findItemsByOrderId(Long orderId);
    int updateStatus(Long orderId, int orderStatus);
}

