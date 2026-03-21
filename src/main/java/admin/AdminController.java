package admin;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import book.BookService;
import book.BookVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/toggle")
	public String toggleAdmin(HttpSession session) {
		Boolean current = (Boolean) session.getAttribute("isAdmin");
		session.setAttribute("isAdmin", current == null ? Boolean.TRUE : !current.booleanValue());
		return "redirect:/";
	}

	@RequestMapping("/books/form")
	public String showBookForm(String isbn, HttpSession session, Model model) {
		if (!isAdmin(session)) {
			return "redirect:/";
		}

		model.addAttribute("mainpage", "admin/bookform");
		if (isbn != null && isbn.trim().length() > 0) {
			model.addAttribute("book", bookService.getBookDetail(isbn));
		}

		return "layout";
	}

	@RequestMapping("/books/save")
	public String saveBook(BookVO book, String publicationDate, String price, String isUpdate, HttpSession session) {
		if (!isAdmin(session)) {
			return "redirect:/";
		}
		if (publicationDate != null && publicationDate.trim().length() > 0) {
			book.setPublicationDate(Date.valueOf(publicationDate));
		}

		if (price != null && price.trim().length() > 0) {
			book.setPrice(Integer.parseInt(price));
		}

		bookService.save(book, "true".equals(isUpdate));
		return "redirect:/books/detail?isbn=" + book.getIsbn();
	}

	@RequestMapping("/books/delete")
	public String deleteBook(String isbn, HttpSession session) {
		if (!isAdmin(session)) {
			return "redirect:/";
		}

		if (isbn != null && isbn.trim().length() > 0) {
			bookService.delete(isbn);
		}

		return "redirect:/books/search";
	}

	private boolean isAdmin(HttpSession session) {
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		return isAdmin != null && isAdmin.booleanValue();
	}
}
