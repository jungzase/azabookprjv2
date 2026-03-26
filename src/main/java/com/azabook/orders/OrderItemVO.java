package com.azabook.orders;

public class OrderItemVO {
    private Long orderItemId;
    private Long orderId;
    private String isbn;
    private String bookName;
    private int quantity;
    private int orderPrice;

    public Long getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Long orderItemId) { this.orderItemId = orderItemId; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getOrderPrice() { return orderPrice; }
    public void setOrderPrice(int orderPrice) { this.orderPrice = orderPrice; }
}