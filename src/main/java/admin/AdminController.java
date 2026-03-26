package admin;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import book.BookService;
import book.BookVO;
import member.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BookService bookService;

	@Autowired
	private MemberService memberService;
	
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
	@RequestMapping("/members")
	public String memberList(HttpSession session, Model model) {
		if (!isAdmin(session)) {
			return "redirect:/";
		}
		model.addAttribute("memberList", memberService.getMemberList());
		model.addAttribute("mainpage", "admin/memberlist");
		return "layout";
	}

	private boolean isAdmin(HttpSession session) {
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		return isAdmin != null && isAdmin.booleanValue();
	}
	
	// 회원 수정 폼 이동
		@RequestMapping("/members/edit")
		public String memberEditForm(@RequestParam("userId") Long userId, HttpSession session, Model model) {
			if (!isAdmin(session)) return "redirect:/";
			
			model.addAttribute("m", memberService.getMemberById(userId));
			model.addAttribute("mainpage", "admin/memberform");
			return "layout";
		}

		// 회원 수정 처리
		@RequestMapping("/members/update")
		public String memberUpdate(member.MemberVO member, HttpSession session) {
			if (!isAdmin(session)) return "redirect:/";
			
			memberService.modifyMemberByAdmin(member);
			return "redirect:/admin/members";
		}

		// 회원 삭제 처리
		@RequestMapping("/members/delete")
		public String memberDelete(@RequestParam("userId") Long userId, HttpSession session) {
			if (!isAdmin(session)) return "redirect:/";
			
			memberService.removeMember(userId);
			return "redirect:/admin/members";
		}
}
