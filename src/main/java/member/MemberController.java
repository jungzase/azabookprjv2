package member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		model.addAttribute("mainpage", "member/join");
		return "layout";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("mainpage", "member/login");
		return "layout";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinok(MemberVO member, RedirectAttributes redirectAttributes) {
		boolean success = memberService.join(member);

		if (success) {
			redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
			return "redirect:/member/login";
		}

		redirectAttributes.addFlashAttribute("message", "회원가입에 실패했습니다. 아이디 중복 여부를 확인하세요.");
		return "redirect:/member/join";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginok(String username, String password, HttpSession session, RedirectAttributes redirectAttributes) {
		MemberVO loginMember = memberService.login(username, password);

		if (loginMember == null) {
			redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
			return "redirect:/member/login";
		}

		session.setAttribute("loginMember", loginMember);
		session.setAttribute("isAdmin", "ADMIN".equalsIgnoreCase(loginMember.getRole()));
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/profile")
	public String profile(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		MemberVO loginMember = getLoginMember(session);
		if (loginMember == null) {
			redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
			return "redirect:/member/login";
		}

		MemberVO member = memberService.getMember(loginMember.getUser_id());
		model.addAttribute("mainpage", "member/profile");
		model.addAttribute("member", member);
		return "layout";
	}

	@RequestMapping("/orders")
	public String orders(HttpSession session, RedirectAttributes redirectAttributes) {
		MemberVO loginMember = getLoginMember(session);
		if (loginMember == null) {
			redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
			return "redirect:/member/login";
		}
		return "redirect:/order/history";
	}

	@RequestMapping("/update")
	public String update(MemberVO member, HttpSession session, RedirectAttributes redirectAttributes) {
		MemberVO loginMember = getLoginMember(session);
		if (loginMember == null) {
			redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
			return "redirect:/member/login";
		}

		member.setUser_id(loginMember.getUser_id());
		member.setRole(loginMember.getRole());

		boolean success = memberService.update(member);
		if (!success) {
			redirectAttributes.addFlashAttribute("message", "회원정보 수정에 실패했습니다.");
			return "redirect:/member/profile";
		}

		MemberVO refreshedMember = memberService.getMember(loginMember.getUser_id());
		session.setAttribute("loginMember", refreshedMember);
		session.setAttribute("isAdmin", "ADMIN".equalsIgnoreCase(refreshedMember.getRole()));
		redirectAttributes.addFlashAttribute("message", "회원정보가 수정되었습니다.");
		return "redirect:/member/profile";
	}

	private MemberVO getLoginMember(HttpSession session) {
		return (MemberVO) session.getAttribute("loginMember");
	}
}
