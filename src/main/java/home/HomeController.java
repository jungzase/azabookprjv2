package home;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import book.BookService;
import book.BookVO;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private BookService bookService;

	@RequestMapping("")
	public String home(Model model) {
		List<BookVO> recommend = Collections.emptyList();

		try {
			recommend = bookService.recommendBooks();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("mainpage", "home/homeMain");
		model.addAttribute("recommend", recommend);
		return "layout";
	}
}
