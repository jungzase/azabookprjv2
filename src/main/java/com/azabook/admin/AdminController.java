package com.azabook.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.azabook.book.BookService;
import com.azabook.book.BookVO;
import com.azabook.member.MemberService;
import com.azabook.orders.OrderService;

@Controller
public class AdminController {

    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/books")
    public String books(Model model) {
        model.addAttribute("bookList", bookService.getAllBooksAdmin());
        return "admin/bookList";
    }

    @GetMapping("/admin/book/form")
    public String bookForm(String isbn, Model model) {
        if (isbn != null && !isbn.isEmpty()) {
            model.addAttribute("book", bookService.getBook(isbn));
        }
        return "admin/bookForm";
    }

    @PostMapping("/admin/book/save")
    public String save(BookVO vo) {
        if (bookService.getBook(vo.getIsbn()) == null) {
            bookService.insert(vo);
        } else {
            bookService.update(vo);
        }
        return "redirect:/admin/books";
    }

    @PostMapping("/admin/book/delete")
    public String delete(String isbn) {
        bookService.delete(isbn);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/members")
    public String members(Model model) {
        model.addAttribute("memberList", memberService.getAllMembers());
        return "admin/memberList";
    }

    @GetMapping("/admin/orders")
    public String orders(Model model) {
        model.addAttribute("orderList", orderService.getAllOrders());
        return "admin/orderList";
    }

    @PostMapping("/admin/order/status")
    public String updateOrderStatus(Long orderId, int orderStatus) {
        orderService.updateStatus(orderId, orderStatus);
        return "redirect:/admin/orders";
    }
}