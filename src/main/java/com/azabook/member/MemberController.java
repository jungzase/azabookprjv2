package com.azabook.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.azabook.orders.OrderService;
import com.azabook.orders.OrderVO;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/member/join")
    public String joinForm() {
        return "member/join";
    }

    @PostMapping("/member/join")
    public String join(MemberVO vo, Model model) {
        boolean result = memberService.join(vo);
        model.addAttribute("msg", result ? "회원가입 완료" : "회원가입 실패");
        return "member/login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(String username, String password, HttpSession session, Model model) {
        MemberVO loginUser = memberService.login(username, password);
        if (loginUser == null) {
            model.addAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
            return "member/login";
        }
        session.setAttribute("loginUser", loginUser);
        return "redirect:/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/member/mypage")
    public String mypage(HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        model.addAttribute("member", memberService.getMember(loginUser.getUserId()));
        return "member/mypage";
    }

    @PostMapping("/member/update")
    public String update(MemberVO vo, HttpSession session) {
        memberService.update(vo);
        session.setAttribute("loginUser", memberService.getMember(vo.getUserId()));
        return "redirect:/member/mypage";
    }

    @GetMapping("/member/orders")
    public String orders(HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        List<OrderVO> orderList = orderService.getOrderListByUser(loginUser.getUserId());
        model.addAttribute("orderList", orderList);
        return "member/orderList";
    }
}