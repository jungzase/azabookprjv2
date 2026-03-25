package com.azabook.orders;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.azabook.cart.CartService;
import com.azabook.member.MemberVO;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/checkout")
    public String checkout(HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        model.addAttribute("cartList", cartService.getCartList(loginUser.getUserId()));
        return "order/checkout";
    }

    @PostMapping("/order/create")
    public String create(OrderVO order, HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        order.setUserId(loginUser.getUserId());

        Long orderId = orderService.createOrder(order);
        model.addAttribute("orderId", orderId);
        return "order/result";
    }
}