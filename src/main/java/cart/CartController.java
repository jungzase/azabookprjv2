package cart;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import member.MemberVO;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart/add")
    public String addFromList(String isbn, Integer quantity, String redirectURL, HttpSession session) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        int orderQuantity = quantity == null || quantity < 1 ? 1 : quantity;
        cartService.add(loginUser.getUserId(), isbn, orderQuantity);
        if (redirectURL != null && redirectURL.startsWith("/") && !redirectURL.startsWith("//")) {
            return "redirect:" + redirectURL;
        }
        return "redirect:/cart/list";
    }

    @PostMapping("/cart/add")
    public String add(String isbn, int quantity, HttpSession session) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        cartService.add(loginUser.getUserId(), isbn, quantity);
        return "redirect:/cart/list";
    }

    @GetMapping("/cart/list")
    public String list(HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        List<CartVO> cartList = cartService.getCartList(loginUser.getUserId());
        model.addAttribute("cartList", cartList);
        return "cart/list";
    }

    @PostMapping("/cart/update")
    public String update(Long cartId, int quantity, HttpSession session) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        cartService.update(cartId, quantity, loginUser.getUserId());
        return "redirect:/cart/list";
    }

    @PostMapping("/cart/delete")
    public String delete(Long cartId, HttpSession session) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        cartService.delete(cartId, loginUser.getUserId());
        return "redirect:/cart/list";
    }
}

