package com.azabook.orders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azabook.book.BookDAO;
import com.azabook.book.BookVO;
import com.azabook.cart.CartDAO;
import com.azabook.cart.CartVO;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public Long createOrder(OrderVO order) {
        List<CartVO> cartList = cartDAO.findByUserId(order.getUserId());
        if (cartList == null || cartList.isEmpty()) {
            return null;
        }

        int totalPrice = 0;
        for (CartVO cart : cartList) {
            totalPrice += cart.getPrice() * cart.getQuantity();
        }
        order.setOrderStatus(0);
        order.setTotalPrice(totalPrice);

        Long orderId = orderDAO.insertOrder(order);

        for (CartVO cart : cartList) {
            BookVO book = bookDAO.findByIsbn(cart.getIsbn());
            if (book.getStock() < cart.getQuantity()) {
                throw new OutOfStockException(book.getBookName(), cart.getQuantity(), book.getStock());
            }

            OrderItemVO item = new OrderItemVO();
            item.setOrderId(orderId);
            item.setIsbn(cart.getIsbn());
            item.setQuantity(cart.getQuantity());
            item.setOrderPrice(cart.getPrice());
            orderDAO.insertOrderItem(item);

            bookDAO.updateStock(cart.getIsbn(), book.getStock() - cart.getQuantity());
        }

        cartDAO.clear(order.getUserId());
        return orderId;
    }

    @Override
    @Transactional
    public Long createDirectOrder(OrderVO order, String isbn, int quantity) {
        BookVO book = bookDAO.findByIsbn(isbn);
        if (book == null) {
            return null;
        }
        if (book.getStock() < quantity) {
            throw new OutOfStockException(book.getBookName(), quantity, book.getStock());
        }

        order.setOrderStatus(0);
        order.setTotalPrice(book.getPrice() * quantity);

        Long orderId = orderDAO.insertOrder(order);

        OrderItemVO item = new OrderItemVO();
        item.setOrderId(orderId);
        item.setIsbn(isbn);
        item.setQuantity(quantity);
        item.setOrderPrice(book.getPrice());
        orderDAO.insertOrderItem(item);

        bookDAO.updateStock(isbn, book.getStock() - quantity);
        return orderId;
    }

    @Override
    public List<OrderVO> getOrderListByUser(Long userId) {
        List<OrderVO> list = orderDAO.findByUserId(userId);
        for (OrderVO vo : list) {
            vo.setItems(orderDAO.findItemsByOrderId(vo.getOrderId()));
        }
        return list;
    }

    @Override
    public List<OrderVO> getAllOrders() {
        List<OrderVO> list = orderDAO.findAll();
        for (OrderVO vo : list) {
            vo.setItems(orderDAO.findItemsByOrderId(vo.getOrderId()));
        }
        return list;
    }

    @Override
    public boolean updateStatus(Long orderId, int status) {
        return orderDAO.updateStatus(orderId, status) > 0;
    }
}
