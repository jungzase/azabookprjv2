package order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.MemberVO;

@Controller
public class OrderController {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	private OrderService orderService;

	@RequestMapping("/order/checkout")
	public String checkout(Model model) {
		model.addAttribute("mainpage", "order/checkout");
		return "layout";
	}

	@RequestMapping("/order/history")
	public String history(String startDate, String endDate, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) {
		MemberVO loginMember = getLoginMember(session);
		if (loginMember == null) {
			redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
			return "redirect:/member/login";
		}

		LocalDate end = parseDate(endDate, LocalDate.now());
		LocalDate start = parseDate(startDate, end.minusMonths(6));
		List<OrderVO> orders = orderService.getOrders(loginMember.getUser_id(), start, end);

		model.addAttribute("mainpage", "order/history");
		model.addAttribute("orders", orders);
		model.addAttribute("statusCounts", orderService.getStatusCounts(orders));
		model.addAttribute("startDate", start.format(DATE_FORMAT));
		model.addAttribute("endDate", end.format(DATE_FORMAT));
		return "layout";
	}

	@RequestMapping("/order/detail")
	public String detail(Long orderId, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		MemberVO loginMember = getLoginMember(session);
		if (loginMember == null) {
			redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
			return "redirect:/member/login";
		}

		if (orderId == null) {
			redirectAttributes.addFlashAttribute("message", "주문번호가 필요합니다.");
			return "redirect:/order/history";
		}

		OrderVO order = orderService.getOrderDetail(orderId, loginMember.getUser_id());
		if (order == null) {
			redirectAttributes.addFlashAttribute("message", "주문 정보를 찾을 수 없습니다.");
			return "redirect:/order/history";
		}

		model.addAttribute("mainpage", "order/detail");
		model.addAttribute("order", order);
		return "layout";
	}

	@RequestMapping("/admin/orders")
	public String manage(Model model) {
		model.addAttribute("mainpage", "order/manage");
		return "layout";
	}

	private MemberVO getLoginMember(HttpSession session) {
		return (MemberVO) session.getAttribute("loginMember");
	}

	private LocalDate parseDate(String value, LocalDate defaultValue) {
		if (value == null || value.trim().length() == 0) {
			return defaultValue;
		}

		try {
			return LocalDate.parse(value, DATE_FORMAT);
		} catch (DateTimeParseException e) {
			return defaultValue;
		}
	}
}
