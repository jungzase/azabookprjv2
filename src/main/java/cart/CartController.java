package cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("mainpage", "cart/list");
		return "layout";
	}
}
