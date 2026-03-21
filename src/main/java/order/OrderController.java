package order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	@RequestMapping("/order/checkout")
	public String checkout(Model model) {
		model.addAttribute("mainpage", "order/checkout");
		return "layout";
	}

	@RequestMapping("/order/history")
	public String history(Model model) {
		model.addAttribute("mainpage", "order/history");
		return "layout";
	}

	@RequestMapping("/admin/orders")
	public String manage(Model model) {
		model.addAttribute("mainpage", "order/manage");
		return "layout";
	}
}
