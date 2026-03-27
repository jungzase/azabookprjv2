package order;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import book.BookService;
import book.BookVO;
import cart.CartService;
import cart.CartVO;
import member.MemberVO;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BookService bookService;

    @GetMapping("/order/checkout")
    public String checkout(HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        model.addAttribute("cartList", cartService.getCartList(loginUser.getUserId()));
        model.addAttribute("directOrder", false);
        return "order/checkout";
    }

    @GetMapping("/order/direct")
    public String directCheckout(String isbn, Integer quantity, HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        BookVO book = bookService.getBook(isbn);
        int orderQuantity = quantity == null || quantity < 1 ? 1 : quantity;

        CartVO directItem = new CartVO();
        directItem.setUserId(loginUser.getUserId());
        directItem.setIsbn(book.getIsbn());
        directItem.setBookName(book.getBookName());
        directItem.setPrice(book.getPrice());
        directItem.setQuantity(orderQuantity);
        directItem.setStock(book.getStock());

        model.addAttribute("cartList", Collections.singletonList(directItem));
        model.addAttribute("directOrder", true);
        model.addAttribute("directIsbn", isbn);
        model.addAttribute("directQuantity", orderQuantity);
        return "order/checkout";
    }

    @PostMapping("/order/create")
    public String create(OrderVO order, String directIsbn, Integer directQuantity, HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        order.setUserId(loginUser.getUserId());

        try {
            Long orderId;
            if (directIsbn != null && !directIsbn.isEmpty()) {
                int quantity = directQuantity == null || directQuantity < 1 ? 1 : directQuantity;
                orderId = orderService.createDirectOrder(order, directIsbn, quantity);
            } else {
                orderId = orderService.createOrder(order);
            }
            model.addAttribute("orderId", orderId);
            return "order/result";
        } catch (OutOfStockException e) {
            model.addAttribute("bookName", e.getBookName());
            model.addAttribute("requestedQuantity", e.getRequestedQuantity());
            model.addAttribute("availableQuantity", e.getAvailableQuantity());
            return "order/outOfStock";
        }
    }
}


