package book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/search")
	public String search(String keyword, String category, Model model) {
		boolean hasAnyParam = keyword != null || category != null;

		model.addAttribute("mainpage", "books/searchMain");
		model.addAttribute("category", category);
		model.addAttribute("keyword", keyword);

		if (hasAnyParam) {
			List<BookVO> books = bookService.search(keyword, category);
			model.addAttribute("books", books);
		}

		return "layout";
	}

	@RequestMapping("/detail")
	public String detail(String isbn, Model model) {
		model.addAttribute("mainpage", "books/detail");
		model.addAttribute("book", bookService.getBookDetail(isbn));
		return "layout";
	}
}
