package book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/list")
    public String list(String keyword, Long categoryId, Model model) {
        List<BookVO> bookList = bookService.getBookList(keyword, categoryId);
        model.addAttribute("bookList", bookList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        return "book/list";
    }

    @GetMapping("/book/new")
    public String newest(Model model) {
        model.addAttribute("bookList", bookService.getNewestBooks());
        return "book/list";
    }

    @GetMapping("/book/detail")
    public String detail(String isbn, Model model) {
        model.addAttribute("book", bookService.getBook(isbn));
        return "book/detail";
    }
}


