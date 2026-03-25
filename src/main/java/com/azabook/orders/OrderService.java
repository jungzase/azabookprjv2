package com.azabook.orders;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderVO order);
    List<OrderVO> getOrderListByUser(Long userId);
    List<OrderVO> getAllOrders();
    boolean updateStatus(Long orderId, int status);
}