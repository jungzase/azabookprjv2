package com.azabook.orders;

import java.sql.Timestamp;
import java.util.List;

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

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
    public int getOrderStatus() { return orderStatus; }
    public void setOrderStatus(int orderStatus) { this.orderStatus = orderStatus; }
    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverHp() { return receiverHp; }
    public void setReceiverHp(String receiverHp) { this.receiverHp = receiverHp; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public String getDeliveryDetailAddress() { return deliveryDetailAddress; }
    public void setDeliveryDetailAddress(String deliveryDetailAddress) { this.deliveryDetailAddress = deliveryDetailAddress; }
    public List<OrderItemVO> getItems() { return items; }
    public void setItems(List<OrderItemVO> items) { this.items = items; }
}