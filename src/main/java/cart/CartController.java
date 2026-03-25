package cart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 장바구니 추가
    @PostMapping("/add")
    public String addCart(CartVO cart, HttpSession session) {
        String memberRank = (String) session.getAttribute("memberRank");
        cart.setMemberRank(memberRank);
        cartService.addCart(cart);
        return "redirect:/cart/list";
    }

    // 수량 수정
    @PostMapping("/update")
    @ResponseBody
    public String updateCart(CartVO cart) {
        cartService.modifyCount(cart);
        return "success";
    }

    // 장바구니 삭제
    @GetMapping("/delete")
    public String deleteCart(int cartId) {
        cartService.deleteCart(cartId);
        return "redirect:/cart/list";
    }
}
