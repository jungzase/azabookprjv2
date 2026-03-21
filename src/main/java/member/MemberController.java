package member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/join")
	public String join(Model model) {
		model.addAttribute("mainpage", "member/join");
		return "layout";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("mainpage", "member/login");
		return "layout";
	}

	@RequestMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("mainpage", "member/profile");
		return "layout";
	}

	@RequestMapping("/orders")
	public String orders(Model model) {
		model.addAttribute("mainpage", "member/orders");
		return "layout";
	}
}
